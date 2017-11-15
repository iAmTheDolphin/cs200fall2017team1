

public class ProviderInterface {
    boolean ProviderValidated = false;
    boolean MemberValidated = false;

   boolean ValidateProvider (int providerID){};//checks to see if provider # is in file
   boolean ValidateMember(int memberID){};//checks to see if member number is in file

    Report requestProviderReport(){};//uh... openservicerecord()?
    int lookupServiceCode(String name){
        //this searches service record file for code pertaining to this
    };

    void CreateServiceRecord(){//creates a service record object, does GenerateServiceRecord from servicerecord class
         };



};