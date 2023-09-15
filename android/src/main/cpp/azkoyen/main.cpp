/**
 * @file main.cpp
 * @author Oscar Pineda (o.pineda@coink.com)
 * @brief Archivo principal basico que llama a las funciones de AzkoyenControl
 * @version 1.1
 * @date 2023-02-15
 * 
 * @copyright Copyright (c) 2023
 * 
 */

//Complacion: g++ -o main main.cpp AzkoyenControl.cpp StateMachine.cpp ValidatorAzkoyen.cpp -I/home/coink/oink/piggybank/connect/coin/Azkoyen/spdlog/include/

#include <stdio.h>
#include <stdbool.h>
#include <iostream>

#include "AzkoyenControl.hpp"

using namespace AzkoyenControl;

int main() {

    printf("main() called.\r\n");

    AzkoyenControlClass AzkoyenControlObject;

    Response_t Respuesta;
    bool FlagContinue = false;

    int Total = 0;

    //Variables externas de escritura parametrizadas [Se deben asignar antes de iniciar el log y de conectar]
    AzkoyenControlObject.WarnToCritical = 10;
    AzkoyenControlObject.MaxCritical = 4;
    AzkoyenControlObject.Path = "logs/Azkoyen.log";
    AzkoyenControlObject.LogLvl = 1;
    AzkoyenControlObject.MaximumPorts = 10;

    AzkoyenControlObject.InitLog();

    Respuesta = AzkoyenControlObject.Connect();
    std::cout<<"Connect retorna: "<<Respuesta.StatusCode<<" Y "<<Respuesta.Message<<std::endl;

    Respuesta = AzkoyenControlObject.CheckDevice();
    std::cout<<"CheckDevice retorna: "<<Respuesta.StatusCode<<" Y "<<Respuesta.Message<<std::endl;

    if ((Respuesta.StatusCode == 200) | (Respuesta.StatusCode == 301)){
        FlagContinue = true;
    }
    
    if (FlagContinue){

        Respuesta = AzkoyenControlObject.StartReader();
        std::cout<<"StartReader retorna: "<<Respuesta.StatusCode<<" Y "<<Respuesta.Message<<std::endl;

        int i=0;
        CoinError_t RespuestaCE;
        while(i<400){

            RespuestaCE = AzkoyenControlObject.GetCoin();
            if (RespuestaCE.StatusCode != 303){
                std::cout<<"GetCoin retorna. StatusCode: "<<RespuestaCE.StatusCode<<" Event: "<<RespuestaCE.Event<<" Coin: "<<RespuestaCE.Coin<<" Message: "<<RespuestaCE.Message<<" Remaining: "<<RespuestaCE.Remaining<<std::endl;    
                if (RespuestaCE.Remaining > 1){
                    CoinLost_t RespuestaLC;
                    RespuestaLC = AzkoyenControlObject.GetLostCoins();
                    std::cout<<"GetLostCoins retorna. 50: "<<RespuestaLC.CoinCinc<<" 100: "<<RespuestaLC.CoinCien<<" 200: "<<RespuestaLC.CoinDosc<<" 500: "<<RespuestaLC.CoinQuin<<" 1000: "<<RespuestaLC.CoinMil<<std::endl;    
                    Total = Total + (RespuestaLC.CoinCinc * 50) + (RespuestaLC.CoinCien * 100) + (RespuestaLC.CoinDosc * 200) + (RespuestaLC.CoinQuin * 500) + (RespuestaLC.CoinMil * 1000);
                }
                if ((RespuestaCE.StatusCode == 402) |(RespuestaCE.StatusCode == 403)){
                    i = 400;
                }
                Total = Total + RespuestaCE.Coin;
            }
            if (i == 200){
                std::cout<<"Quitando ambas monedas de 500....."<<std::endl;
                Respuesta = AzkoyenControlObject.ModifyChannels(191,175);
                std::cout<<"Modify channels retorna: "<<Respuesta.StatusCode<<" Y "<<Respuesta.Message<<std::endl;
            }
            i++;
        }

        Respuesta = AzkoyenControlObject.CheckDevice();
        std::cout<<"CheckDevice retorna: "<<Respuesta.StatusCode<<" Y "<<Respuesta.Message<<std::endl;
        
        Respuesta = AzkoyenControlObject.StopReader();
        std::cout<<"StopReader retorna: "<<Respuesta.StatusCode<<" Y "<<Respuesta.Message<<std::endl;

        //No es necesario correr Reset Device (Solo se creo para la pantalla de mantenimiento)
        Respuesta = AzkoyenControlObject.ResetDevice();
        std::cout<<"ResetDevice retorna: "<<Respuesta.StatusCode<<" Y "<<Respuesta.Message<<std::endl;

        std::cout<<"Total depositado: "<<Total<<" COP "<<std::endl;

        TestStatus_t Status = AzkoyenControlObject.TestStatus();
        std::cout<<"TestStatus retorna. Version: "<<Status.Version<<" Device: "<<Status.Device<<std::endl;
        std::cout<<"TestStatus retorna. ErrorType: "<<Status.ErrorType<<" ErrorCode: "<<Status.ErrorCode<<std::endl;
        std::cout<<"TestStatus retorna. Message: "<<Status.Message<<" AditionalInfo: "<<Status.AditionalInfo<<std::endl;
        std::cout<<"TestStatus retorna. Priority: "<<Status.Priority<<std::endl;
    }
    
    return 0;
}