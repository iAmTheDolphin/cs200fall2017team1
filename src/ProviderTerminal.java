

//this will be like the starting thing i guess?

/**
 steps
 display login screen, with a box to enter provider number and a button to click if operator
 if operator, switch to operatorterminal
 if provider, validate-- read in provider number text file and turn into array
 then it switches to the next thing for providers, who have these options:
 give service
 open service file
 generate provider record
 open provider records
 log out
*/

public class ProviderTerminal {

    public void Start(){
    //char try = 'y';
    //while (try == 0) {
    System.out.println("\nPlease enter your Provider ID: ");
    int ID = 0; //= input
    ProviderInterface x = new ProviderInterface();
    boolean check = x.ValidateProvider(ID);
    if (check) {
        boolean menu = false;
        while (!menu){
        System.out.println("\nMenu \n1. Give Service \n2. Create Service Record\n3. Log Out\n"); //add more if necessary
        String input = "0";// = input;
        if (input == "1"){menu=true;}
        else if (input == "2"){menu=true;}
        else if (input == "3"){menu=true;}
        else {
            System.out.println("\nSorry, that is not an option. Please try again. \n");
        }}

    }
    else {
        System.out.println("\nSorry, that is an incorrect Provider ID. Try again? (Y/N) ");
        //try = input
    }}
   // }


}