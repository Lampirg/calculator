# calculator

## Description

This is a simple calculator that performs main arithmetic operations (addition, subtraction, multiplication, division). Number of operations can be arbitrary. Operations can be systemized with round brackets.

Calculator does not perform any kind of validation but it removes all spaces from input.

Application was created using Spring Boot framework.

## Usage

This application is not deployed anywhere so the only way to use it is to clone repository and run it manually. If it was started, you can access it with "/calc" endpoint (through localhost:8080).

There are 2 ways to use it:

1. Pass expression to calculate through body in POST request.
2. Pass expression as path variable ("/calc/{expression}").
   
Response will be single string containing answer for given expression.