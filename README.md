# Desigen-Pattrens
In this projects , i use the pattrens : Decorate ,Memento ,Singelton

Decorate : for implements decorate i create  3 class : one is addressbook-interface ,addresbook-abstract, decorate button.

In interface  i define the method for decorate the addressbook that abstrac class implements

In addresbook-abstract : if i want also deffierent  decorate all i need is to build class that  extend from this class and implements
the method for decorate and constructor

decorate button : i define  three buttons and add them to addressbook and extend the class addresbook-abstract(addressDecorator)
and implement the method getaddresbook

Memento : i save the address in the stack and push when i click undo and pop when i  click redo.

Singelton : i restrict the amount of addres book to 3   by the constructor be  private and create method that limit instance to 3


