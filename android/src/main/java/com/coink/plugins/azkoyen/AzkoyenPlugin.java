package com.coink.plugins.azkoyen;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Azkoyen")
public class AzkoyenPlugin extends Plugin implements CoinNotifier {
    private static final String COIN_EVENT = "coinInsert";
    private static final String COIN_WARNING_EVENT = "coinInsertWarning";
    private static final  String TAG = "AzkoyenPlugin";

    private Azkoyen implementation;

    @Override
    public void load() {
        super.load();
        implementation = new Azkoyen(this);
    }

    @PluginMethod()
    public void connect(PluginCall call) {
        try {
            AzkoyenResponse response = implementation.connect();
            call.resolve(response);
        } catch (AzkoyenException e) {
            String message = e.getMessage();
            String code = e.getCode();
            call.reject(message, code);
        }
    }

    @PluginMethod()
    public void checkDevice(PluginCall call) {
        try {
            AzkoyenResponse response = implementation.checkDevice();
            call.resolve(response);
        } catch (AzkoyenException e) {
            String message = e.getMessage();
            String code = e.getCode();
            call.reject(message, code);
        }
    }

    @PluginMethod()
    public void testStatus(PluginCall call) {
        AzkoyenResponse response = implementation.testStatus();
        call.resolve(response);
    }

    @PluginMethod()
    public void modifyChannel(PluginCall call) {
        int channel = call.getInt("channel", 0);
        boolean active = call.getBoolean("active", true);
        try {
            AzkoyenResponse response = implementation.modifyChannel(channel, active);
            call.resolve(response);
        } catch (AzkoyenException e) {
            String message = e.getMessage();
            String code = e.getCode();
            call.reject(message, code);
        }
    }

    @PluginMethod()
    public void init(PluginCall call) {
        try {
            implementation.connect();
            implementation.checkDevice();
            call.resolve();
        } catch (AzkoyenException e) {
            String message = e.getMessage();
            String code = e.getCode();
            call.reject(message, code);
        }
    }

    @PluginMethod()
    public void startReader(PluginCall call) {
        try {
            AzkoyenResponse response = implementation.startReader(this);
            call.resolve(response);
        } catch (AzkoyenException e) {
            String message = e.getMessage();
            String code = e.getCode();
            call.reject(message, code);
        }
    }

    @PluginMethod()
    public void stopReader(PluginCall call) {
        try {
            AzkoyenResponse response = implementation.stopReader();
            call.resolve(response);
        } catch (AzkoyenException e) {
            String message = e.getMessage();
            String code = e.getCode();
            call.reject(message, code);
        }
    }

    @PluginMethod()
    public void reset(PluginCall call) {
        try {
            AzkoyenResponse response = implementation.reset();
            call.resolve(response);
        } catch (AzkoyenException e) {
            String message = e.getMessage();
            String code = e.getCode();
            call.reject(message, code);
        }
    }

    @Override
    public void onInsertCoin(JSObject data) {
        notifyListeners(COIN_EVENT, data);
    }

    @Override
    public void onInsertCoinWarning(JSObject data) {
        notifyListeners(COIN_WARNING_EVENT, data);
    }

}
