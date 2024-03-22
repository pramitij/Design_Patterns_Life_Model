# Life Model
>The Life Model simulates the life of individuals by modeling their attributes using various design patterns including Builder, Command, Decorator, Factory, and Singleton.

## Table of Contents
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Design Patterns Used](#design-patterns-used)
- [Demo](#demo)


## Requirements
  Eclipse or VS Code or IntelliJ.

## Installation
1. Clone the repository on your local system:
   ```sh
   git clone https://github.com/yourusername/life-model.git
   
Alternatively, for Eclipse Import the project as Existing Maven Project, For IntelliJ you can directlty open it using 'Get from VCS'.

2. Navigate to the project directory:
   ```sh
   cd life-model

3. Compile and run the demo code:
   ```sh
    javac Life.java
    java Life
   
##Usage

Create instances of Person objects and apply decorators to simulate different life milestones.
Customize the Person attributes as needed using the provided methods.

##Design Patterns Used

Builder
Command
Decorator
Factory
Singleton

###Person Class and Builder Design Pattern:
-The Person class represents a person with attributes like ID, age, name, description, and date of birth.
-It uses the Builder design pattern (PersonBuilder) to construct Person objects with optional parameters.
###Factory Design Pattern:
-The PersonFactoryAPI interface and PersonFactory class demonstrate the Factory design pattern for creating Person objects.
###Singleton Design Pattern:
-The PersonFactorySingleton class implements the Singleton pattern using an enum to ensure only one instance of the factory is created.
###Decorator Design Pattern:
-Several concrete decorator classes (ResponsiblePersonDecorator, WisePersonDecorator, StudentPersonDecorator, EngineerPersonDecorator, SeniorPersonDecorator) add specific behaviors (like adding descriptions and modifying ages) to Person objects.
###Command Design Pattern:
The PersonCommand interface and CreatePersonCommand class use the Command pattern to create and decorate Person objects.

##Demo
Execute the demo method in the Life class to see a demonstration of the program functionality.


