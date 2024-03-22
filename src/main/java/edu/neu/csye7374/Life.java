package edu.neu.csye7374;

/**
 * Life model
 * 
 * 100 TOTAL POINTS
 * 
 * @author  PramithiJagdish
 * 
 * model the life of persons which age older as they reach milestones in their lives
 * 
 * Develop using following design patterns: Builder, Command, Decorator, Factory, Singleton:
 * 
 * Person attributes (ID, AGE, Description, Date of Birth):
 * 
 * 				"11,23,Sam,serious,1996-04-17",
 *				"12,22,Cam,curious,1996-02-17",
 *				"13,21,Pam,patient,1996-01-17"
 *
 * use to Decorator design pattern for Person objects to add to Person description and age:
 * 
 * 25 POINTS: create the following Person Decorators
 * 
 * ResponsiblePersonDecorator: adds "responsible" to description and 10 years to age
 * WisePersonDecorator: adds "wise" to description and 20 years to age
 * StudentPersonDecorator: adds "and is in year 2 at NEU as an MGEN student" to description and 2 years to age
 * EngineerPersonDecorator: adds "and is employed as an engineer with 10 years work experience" to description and 10 years to age
 * SeniorPersonDecorator: adds "and is a senior citizen living 20 in retirement" to description and 20 years to age
 * 
 * MUST use Command design pattern to create Person object AND Decorate Person object (SEVERAL COMMANDS)
 * 
 * EXECUTE THE FOLLOWING IN THE FOLLOWING ORDER:
 * 
 * 15 POINTS: create EACH Person object (with a Singleton Person Factory accepting a PersonBuilder object)
 *  
 *  	STEP 1: create Sam
 *  	STEP 2: create Cam
 *  	STEP 3: create Pam
 *  
 * 30 POINTS: add to Person age and description (using one or more appropriate Person Decorators):
 *  
 *  	STEP 4: add responsible to Sam
 *  	STEP 5: add wise to Cam
 *  	STEP 6: add wise and responsible to Pam
 *  
 *  	STEP 7: add student to Sam
 *  	STEP 8: add engineer to Cam
 *  	STEP 9: add senior to Pam
 *  
 * 30 POINTS: SHOW (Person State) OUTPUT TO CONSOLE (IN ORDER) to demonstrate life milestones:
 * 
 *  	Person Sam (STEP 1)
 *  	Person Cam (STEP 2)
 *  	Person Pam (STEP 3)
 *  
 *  	Person Sam (STEP 4)
 *  	Person Cam (STEP 5)
 *  	Person Pam (STEP 6)
 *  
 *  	Person Sam (STEP 7)
 *  	Person Cam (STEP 8)
 *  	Person Pam (STEP 9)
 */
public class Life {

	/**
	 * All inner classes from here
	 */

	/**
	 * PersonFactoryAPI interface using Factory method design pattern
	 */
	private interface PersonFactoryAPI {
		Person getObject(Person.PersonBuilder b);
	}

	/**
	 * PersonFactory class using Factory method design pattern
	 */

	public static class PersonFactory implements PersonFactoryAPI {

		@Override
		public Person getObject(Person.PersonBuilder b) {
			return new Person(b);
		}
	}


	/**
	 * PersonFactoryEnumSingleton using the Factory Method pattern and the Singleton pattern with an enum
	 */
	private static class PersonFactorySingleton implements PersonFactoryAPI {

		private static final PersonFactoryAPI instance = new PersonFactorySingleton();

		private static PersonFactoryAPI getInstance() {
			return instance;
		}

		@Override
		public Person getObject(Person.PersonBuilder b) {
			return new Person(b);
		}
	}

	/**
	 * Person class using Builder Design Pattern
	 */
	public static class Person {
		private int id;

		public void setId(int id) {
			this.id = id;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		private int age;
		private String name;
		private String description;
		private String dob;

		public int getId() {
			return this.id;
		}

		public int getAge() {
			return this.age;
		}

		public String getName() {
			return this.name;
		}

		public String getDescription() {
			return this.description;
		}

		public String getDob() {
			return this.dob;
		}

		public Person(PersonBuilder personBuilder) {
			this.id = personBuilder.id;
			this.age = personBuilder.age;
			this.name = personBuilder.name;
			this.description = personBuilder.description;
			this.dob = personBuilder.dob;
		}

		public Person() {
		}
		@Override
		public String toString(){
			return "PERSON ID: "+getId()+"NAME: "+getName()+ "DESCRIPTION: "+getDescription()+"AGE: "+getAge()+"DOB: " +getDob();
		}

		public static class PersonBuilder {

			private int id;
			private int age;
			private String name;
			private String description;
			private String dob;

			public PersonBuilder() {
				this.id = 0;
				this.age = 0;
				this.name = "";
				this.description = "";
				this.dob = "";
			}

			public PersonBuilder setId(int id) {
				this.id = id;
				return this;
			}

			public PersonBuilder setAge(int age) {
				this.age = age;
				return this;
			}

			public PersonBuilder setName(String name) {
				this.name = name;
				return this;
			}

			public PersonBuilder setDescription(String description) {
				this.description = description;
				return this;
			}

			public PersonBuilder setDob(String dob) {
				this.dob = dob;
				return this;
			}

			public Person build() {
				return new Person(this);
			}

		}
	}

	/**
	 * Command interface for creating and decorating Person objects
	 */
	private interface PersonCommand {
		void execute();
		Person getResult();
	}

	private interface PersonDecorator {
		Person getPerson();
		void decorate();
	}

	private static class CreatePersonCommand implements PersonCommand {
		private Person.PersonBuilder builder;
		private Person result;

		public CreatePersonCommand(Person.PersonBuilder builder) {
			this.builder = builder;
		}

		@Override
		public void execute() {
			result = new Person(builder);
		}

		@Override
		public Person getResult() {
			return result;
		}
	}

	/**
	 * Concrete decorator that adds "responsible" to description and 10 years to age
	 */
	private static class ResponsiblePersonDecorator implements PersonDecorator {
		private Person person;

		public ResponsiblePersonDecorator(Person person) {
			this.person = person;
		}

		@Override
		public Person getPerson() {
			return this.person;
		}

		@Override
		public void decorate() {
			person.setDescription(person.getDescription() + ", responsible");
			person.setAge(person.getAge() + 10);
		}
	}

	/**
	 * Concrete decorator that adds "wise" to description and 20 years to age
	 */
	private static class WisePersonDecorator implements PersonDecorator {
		private Person person;

		@Override
		public Person getPerson() {
			return this.person;
		}

		public WisePersonDecorator(Person person) {
			this.person = person;
		}

		@Override
		public void decorate() {
			person.setDescription(person.getDescription() + ", wise");
			person.setAge(person.getAge() + 20);
		}
	}

	/**
	 * Concrete decorator that adds "and is in year 2 at NEU as an MGEN student" to description and 2 years to age
	 */
	private static class StudentPersonDecorator implements PersonDecorator {
		private Person person;

		@Override
		public Person getPerson() {
			return this.person;
		}

		public StudentPersonDecorator(Person person) {
			this.person = person;
		}

		@Override
		public void decorate() {
			person.setDescription(person.getDescription() + ", and is in year 2 at NEU as an MGEN student");
			person.setAge(person.getAge() + 2);
		}
	}

	/**
	 * Concrete decorator that adds "and is employed as an engineer with 10 years work experience" to description and 10 years to age
	 */
	private static class EngineerPersonDecorator implements PersonDecorator {
		private Person person;

		@Override
		public Person getPerson() {
			return this.person;
		}

		public EngineerPersonDecorator(Person person) {
			this.person = person;
		}

		@Override
		public void decorate() {
			person.setDescription(person.getDescription() + ", and is employed as an engineer with 10 years work experience");
			person.setAge(person.getAge() + 10);
		}
	}

	/**
	 * Concrete decorator that adds "and is a senior citizen living 20 in retirement" to description and 20 years to age
	 */
	private static class SeniorPersonDecorator implements PersonDecorator {
		private Person person;

		@Override
		public Person getPerson() {
			return this.person;
		}

		public SeniorPersonDecorator(Person person) {
			this.person = person;
		}

		@Override
		public void decorate() {
			person.setDescription(person.getDescription() + ", and is a senior citizen living 20 in retirement");
			person.setAge(person.getAge() + 20);
		}
	}


	/**
		 * demonstrate the use of this company class
		 */
		public static void demo() {
			System.out.println("\n\t" + Life.class.getName() + ".demo() ...");

			Life company = new Life();
			company.CreatePersons();
			System.out.println(company);


			System.out.println("\n\t" + Life.class.getName() + ".demo() ... done!");
		}
		@Override
		public String toString(){
			Life company = new Life();
			return company.CreatePersons();
		}
		public String CreatePersons() {
			PersonFactoryAPI factory = PersonFactorySingleton.getInstance();

			// Create Sam
			Person sam = factory.getObject(new Person.PersonBuilder()
					.setId(1)
					.setAge(25)
					.setName("Sam")
					.setDescription("A programmer")
					.setDob("1998-01-01")
			);
			//System.out.println(sam);
			// Create Cam
			Person cam = factory.getObject(new Person.PersonBuilder()
					.setId(2)
					.setAge(30)
					.setName("Cam")
					.setDescription("A writer")
					.setDob("1993-05-15")
			);
			//System.out.println(cam);
			// Create Pam
			Person pam = factory.getObject(new Person.PersonBuilder()
					.setId(3)
					.setAge(40)
					.setName("Pam")
					.setDescription("An artist")
					.setDob("1983-11-25")
			);
			//System.out.println(pam);

			String output = sam+"\n"+cam+"\n"+pam+"\n";
//
//			STEP 4: add responsible to Sam
// *  	STEP 5: add wise to Cam
// *  	STEP 6: add wise and responsible to Pam
// *
// *  	STEP 7: add student to Sam
// *  	STEP 8: add engineer to Cam
// *  	STEP 9: add senior to Pam

			// Create a ResponsiblePersonDecorator for Sam
			PersonDecorator responsibleSam = new ResponsiblePersonDecorator(sam);

			// Create a WisePersonDecorator for Cam
			PersonDecorator wiseCam = new WisePersonDecorator(cam);

			// Create a StudentPersonDecorator for Sam
			PersonDecorator studentSam = new WisePersonDecorator(pam);

			// Create an EngineerPersonDecorator for Cam
			PersonDecorator engineerSam = new EngineerPersonDecorator(cam);

			// Create a SeniorPersonDecorator for Pam
			PersonDecorator seniorSam = new SeniorPersonDecorator(pam);

			// Print out the decorated Sam

			output = output + " \n"+ "Decorated Sam: " + responsibleSam.getPerson().getDescription() + ", " + responsibleSam.getPerson().getAge() + " years old"+"\nDecorated Cam: " + wiseCam.getPerson().getDescription() + ", " + wiseCam.getPerson().getAge() + " years old"+"\nDecorated Pam: " + studentSam.getPerson().getDescription() + ", " + studentSam.getPerson().getAge() + " years old"+"\nDecorated Cam: " + engineerSam.getPerson().getDescription() + ", " + engineerSam.getPerson().getAge() + " years old"+"\nDecorated Pam: " + seniorSam.getPerson().getDescription() + ", " + seniorSam.getPerson().getAge() + " years old";

			return output;

//			System.out.println("Decorated Sam: " + responsibleSam.getPerson().getDescription() + ", " + responsibleSam.getPerson().getAge() + " years old");
//			System.out.println("Decorated Cam: " + wiseCam.getPerson().getDescription() + ", " + wiseCam.getPerson().getAge() + " years old");
//			System.out.println("Decorated Pam: " + studentSam.getPerson().getDescription() + ", " + studentSam.getPerson().getAge() + " years old");
//			System.out.println("Decorated Cam: " + engineerSam.getPerson().getDescription() + ", " + engineerSam.getPerson().getAge() + " years old");
//			System.out.println("Decorated Pam: " + seniorSam.getPerson().getDescription() + ", " + seniorSam.getPerson().getAge() + " years old");
		}
	}

/**
 * Output
 */
//edu.neu.csye7374.Life.demo() ...
//PERSON ID: 1NAME: SamDESCRIPTION: A programmerAGE: 25DOB: 1998-01-01
//PERSON ID: 2NAME: CamDESCRIPTION: A writerAGE: 30DOB: 1993-05-15
//PERSON ID: 3NAME: PamDESCRIPTION: An artistAGE: 40DOB: 1983-11-25
//
//Decorated Sam: A programmer, 25 years old
//Decorated Cam: A writer, 30 years old
//Decorated Pam: An artist, 40 years old
//Decorated Cam: A writer, 30 years old
//Decorated Pam: An artist, 40 years old
//
//	edu.neu.csye7374.Life.demo() ... done!
//
//
//============Main Execution End===================
//
//Process finished with exit code 0
