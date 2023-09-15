%module AzkoyenControl
%include "std_string.i"
%include "ValidatorAzkoyen.hpp"
%include "StateMachine.hpp"
%include "AzkoyenControl.hpp"
%{
#include "ValidatorAzkoyen.hpp"
#include "StateMachine.hpp"
#include "AzkoyenControl.hpp"
using namespace AzkoyenControl;
using namespace StateMachine;
using namespace ValidatorAzkoyen;
%}
