package com.coink.plugins.azkoyen;

import android.util.Pair;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginConfig;

import java.util.HashMap;
import java.util.Map;

import hardware.azkoyen.AzkoyenControlClass;
import hardware.azkoyen.CoinError_t;
import hardware.azkoyen.CoinLost_t;
import hardware.azkoyen.Response_t;
import hardware.azkoyen.TestStatus_t;

public class Azkoyen implements Runnable {

    static {
        System.loadLibrary("Azkoyen_Wrapper");
    }

    private static final String LOG_FILE = "Azkoyen.log";

    private final AzkoyenControlClass azkoyen = new AzkoyenControlClass();
    private final CoinsChannels channels = new CoinsChannels();
    private final CustomThread thread = new CustomThread(this);

    private CoinNotifier notifier;

    public Azkoyen(Plugin plugin) {
        PluginConfig config = plugin.getConfig();
        int warnToCritical = config.getInt("warnToCritical", 10);
        int maxCritical = config.getInt("maxCritical", 3);
        int MaximumPorts = config.getInt("maximumPorts", 10);
        int logLevel = config.getInt("logLevel", 1);

        azkoyen.setWarnToCritical(warnToCritical);
        azkoyen.setMaxCritical(maxCritical);
        azkoyen.setMaximumPorts(MaximumPorts);

        String packagePath = plugin.getActivity().getApplicationContext().getFilesDir().getAbsolutePath();
        String logPath = packagePath + "/" + LOG_FILE;
        azkoyen.setPath(logPath);
        azkoyen.setLogLvl(logLevel);

        azkoyen.InitLog();
    }

    public AzkoyenResponse connect() throws AzkoyenException {
        thread.pause();
        Response_t response = azkoyen.Connect();
        thread.resume();
        if (response.getStatusCode() != 200 && response.getStatusCode() != 301) {
            throw new AzkoyenException(response);
        }
        return new AzkoyenResponse(response);
    }

    public AzkoyenResponse checkDevice() throws AzkoyenException {
        thread.pause();
        Response_t response = azkoyen.CheckDevice();
        thread.resume();
        if (response.getStatusCode() != 200 && response.getStatusCode() != 301) {
            throw new AzkoyenException(response);
        }
        return new AzkoyenResponse(response);
    }

    public AzkoyenResponse testStatus() {
        thread.pause();
        TestStatus_t result = azkoyen.TestStatus();
        thread.resume();
        return new AzkoyenResponse(result);
    }

    public AzkoyenResponse modifyChannel(int channel, boolean active) throws AzkoyenException {
        channels.setChannel(channel, active);
        Pair<Integer, Integer> values = channels.getValue();
        thread.pause();
        Response_t response = azkoyen.ModifyChannels(values.first, values.second);
        thread.resume();
        int status = response.getStatusCode();
        if (status != 203) {
            throw new AzkoyenException(response);
        }
        return new AzkoyenResponse(response, channels);
    }

    public AzkoyenResponse startReader(CoinNotifier notifier) throws AzkoyenException {
        thread.stop();
        Response_t response = azkoyen.StartReader();
        int status = response.getStatusCode();
        if (status != 201 && status != 301 && status != 300) {
            throw new AzkoyenException(response);
        }
        channels.reset();
        this.notifier = notifier;
        thread.start();
        return new AzkoyenResponse(response);
    }

    public AzkoyenResponse stopReader() throws AzkoyenException {
        thread.stop();
        this.notifier = null;
        channels.reset();
        Response_t response = azkoyen.StopReader();
        int status = response.getStatusCode();
        if (status != 200 && status != 301) {
            throw new AzkoyenException(response);
        }
        return new AzkoyenResponse(response);
    }

    public AzkoyenResponse reset() throws AzkoyenException {
        Response_t response = azkoyen.ResetDevice();
        int status = response.getStatusCode();
        if (status != 204) {
            throw new AzkoyenException(response);
        }
        return new AzkoyenResponse(response);
    }

    @Override
    public void run() {
        CoinError_t coin = azkoyen.GetCoin();
        int status = coin.getStatusCode();

        if (status == 303) return;

        if (coin.getRemaining() > 1) {
            CoinLost_t remaining = azkoyen.GetLostCoins();
            Map<Integer, Integer> coinList = new HashMap<>();
            coinList.put(50, remaining.getCoinCinc());
            coinList.put(100, remaining.getCoinCien());
            coinList.put(200, remaining.getCoinDosc());
            coinList.put(500, remaining.getCoinQuin());
            coinList.put(1000, remaining.getCoinMil());
            for (Map.Entry<Integer, Integer> entry : coinList.entrySet()) {
                int quantity = entry.getValue();
                int coinValue = entry.getKey();
                if (quantity == 0) continue;
                AzkoyenEvent event = AzkoyenEvent.fromValue(coinValue);
                for (int i = 0; i < quantity; i++) {
                    this.notifier.onInsertCoin(event);
                }
            }
        }

        if (status == 302 || status == 404) return;

        if (status == 401) {
            AzkoyenEvent warning = AzkoyenEvent.warning(coin);
            notifier.onInsertCoinWarning(warning);
            return;
        }

        if (status != 202) {
            AzkoyenEvent error = AzkoyenEvent.error(coin);
            this.notifier.onInsertCoin(error);
            thread.breakProcess();
            return;
        }

        AzkoyenEvent event = new AzkoyenEvent(coin);
        this.notifier.onInsertCoin(event);
    }

}
