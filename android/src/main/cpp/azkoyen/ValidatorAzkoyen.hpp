/**
 * @file ValidatorAzkoyen.hpp
 * @author Oscar Pineda (o.pineda@coink.com)
 * @brief Header de las funciones del validador Azkoyen
 * @version 1.1
 * @date 2023-05-17
 * 
 * @copyright Copyright (c) 2023
 * 
 */

#ifndef VALIDATORAzkoyen
#define VALIDATORAzkoyen

#include <stdio.h>
#include <cstring> // To include strerror
#include <fcntl.h> // Contains file controls like O_RDWR
#include <errno.h> // To include errno
#include <termios.h> // Contains POSIX terminal control definitions
#include <unistd.h> // write(), read(), close()
#include <sys/ioctl.h> //To use flush
#include <bitset> //To use bitset in HandleResponseInfo

#include "spdlog/spdlog.h" //Logging library
#include "spdlog/sinks/daily_file_sink.h" //Logging library - daily file

#include <vector>

namespace ValidatorAzkoyen{

    struct SpdlogLevels_t{
        int Code;
        std::string Message;
    };

    struct ErrorCodePolling_t{
        int Code;
        std::string Message;
        int Static;
        int Critical;
    };

    struct CoinPolling_t{
        int Channel;
        int Coin;
    };

    struct ErrorCodeExComm_t{
        int Code;
        std::string Message;
    };

    struct FaultCode_t{
        int Code;
        std::string Message;
    };


    class AzkoyenClass{
        public:

            // --------------- EXTERNAL VARIABLES --------------------//

            // READ ONLY

            /**
             * @brief Descriptor de archivo del puerto del validador
             */
            int SerialPort;

            /**
             * @brief Bandera que incica si fue exitosa la conexion al puerto serial del validador
             */
            bool SuccessConnect;

            /**
             * @brief Este puerto es el resultante del escaneo de la funcion ScanPorts
             */

            int PortO;

            /**
             * @brief Evento actual en el validador (Funcion StPolling)
             */
            int CoinEvent;

            /**
             * @brief Evento anterior en el validador (Funcion StPolling)
             */
            int CoinEventPrev;

            int CoinCinc;
            int CoinCien;
            int CoinDosc;
            int CoinQuin;
            int CoinMil;
            
            /**
             * @brief Bandera que indica si sucedio un error (Funcion StPolling)
             */
            bool ErrorHappened;

            /**
             * @brief Bandera que indica si sucedio un error critico en alguno de los 5 eventos (Funcion StPolling)
             */
            bool CriticalError;

            /**
             * @brief Codigo de error de polling
             */
            int ErrorOCode;

            /**
             * @brief Mensaje de error de polling
             */
            std::string ErrorOMsg;

            /**
             * @brief Indica si la moneda fue rechazada mientras corria 'polling'
             */
            int ErrorOStatic;

            /**
             * @brief Indica si el error es tan critico como para cerrar el proceso
             */
            int ErrorOCritical;
            
            int FaultOCode;
            std::string FaultOMsg;

            bool NoUsedBit;
            bool MeasurePhotoBlocked;
            bool OutPhotoBlocked;
            bool COSAlert;

            /**
             * @brief Ultima moneda reconocida
             */
            int ActOCoin;
            /**
             * @brief Ultimo canal detectado asociado a la moneda reconocida
             */
            int ActOChannel;

            // WRITE ONLY

            /**
             * @brief Nivel de logging de la libreria Spdlog
             */
            int LoggerLevel;

            /**
             * @brief Ruta donde se guardan los logs de Spdlog
             */
            std::string LogFilePath;

            /**
             * @brief Cantidad maxima de puertos que escanea la funcion ScanPorts()
             */
            int MaxPorts;

            // --------------- CONSTRUCTOR FUNCTIONS --------------------//

            /**
             * @brief Constructor de la clase AzkoyenClass
             */
            AzkoyenClass();
            
            /**
             * @brief Destructor de la clase AzkoyenClass
             */
            ~AzkoyenClass();

            // --------------- LOGGER FUNCTIONS --------------------//

            SpdlogLevels_t SearchSpdlogLevel(int Code);
            void SetSpdlogLevel();

            // --------------- SEARCH FUNCTIONS --------------------//

            ErrorCodeExComm_t SearchErrorCodeExComm (int Code);
            CoinPolling_t SearchCoin (int Channel);
            ErrorCodePolling_t SearchErrorCodePolling (int Code);
            FaultCode_t SearchFaultCode (int Code);

            // --------------- STATES OF MACHINE STATE (FUNCTIONS) --------------------//

            int StIdle();
            int StConnect();
            int StCheck();
            int StWaitPoll();
            int StPolling();
            int StReset();
            int StError();

            // --------------- MAIN FUNCTIONS --------------------//
            
            /**
            * @brief Abre el archivo donde se van a guardar los logs
            * @param Path Ruta donde se debe guardar el log de Spdlog
            */
            void InitLogger(const std::string& Path);
            
            int ConnectSerial(int Port);
            int ScanPorts();
            int SendingCommand(std::vector<unsigned char> Comm);
            int ExecuteCommand(std::vector<unsigned char> Comm);
            int HandleResponse(std::vector<unsigned char> Response, int Rdlen, int Xlen);
            int HandleResponsePolling(std::vector<unsigned char> Response, int Rdlen);
            int HandleResponseInfo(std::vector<unsigned char> Response, int Rdlen);
            int CheckOptoStates();
            int SimplePoll();
            int SelfCheck();
            int EnableChannels();
            int CheckEventReset();
            int ResetDevice();
            std::vector<unsigned char> BuildCmdModifyInhibit(int InhibitMask1, int InhibitMask2);
            int ChangeInhibitChannels(int InhibitMask1, int InhibitMask2);
    };
}

#endif /* VALIDATORAzkoyen */