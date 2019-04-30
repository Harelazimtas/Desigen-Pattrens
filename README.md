# Desigen-Pattrens
In this projects ,i use the pattrens : Decorate ,Memento ,Singelton

Decorate : for implements decorate i create three class: addressbook-interface ,addresbook-abstract, decorate button.

In interface : I define the method for decorate the addressbook that abstrac class implements

In addresbook-abstract : If i want also deffierent  decorate all i need is to build class that  extend from this class and implements
the method for decorate and constructor

decorate button : I define  three buttons and add them to addressbook and extend the class addresbook-abstract(addressDecorator)
and implement the method getaddresbook

Memento : I save the address in the stack and push when i click undo and pop when i  click redo.

Singelton : I restrict the amount of addres book to three by do private the constructor and create method that limit the instance to three


