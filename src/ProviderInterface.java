

public class ProviderInterface {
    boolean ProviderValidated = false;
    boolean MemberValidated = false;

   boolean ValidateProvider (int providerID){
       DatabaseController x = new DatabaseController();
       Provider check = x.getProvider(providerID);
       if (check.getName() != "-1") {
           System.out.println("Welcome, " + check.getName());
            return true;}
       return false;}//checks to see if provider # is in file
   boolean ValidateMember(int memberID){       DatabaseController x = new DatabaseController();
       Member check = x.getMember(memberID);
       if (check.getName() != "-1") {
           System.out.println("Welcome, " + check.getName());
           return true;}
       return false;}//checks to see if member number is in file

    //Report requestProviderReport(){};//uh...
    int lookupServiceCode(String name){
        //this searches service record file for code pertaining to this.....
        ServiceCode x = new ServiceCode();
        return x.FindServiceCode(name);
    }

    void CreateServiceRecord(){//creates a service record object, does GenerateServiceRecord from servicerecord class
        ServiceRecord x = new ServiceRecord();
        x.GenerateServiceRecord();
         }



}