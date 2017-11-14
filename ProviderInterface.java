

public class ProviderInterface {

    boolean ProviderValidated = false;

   boolean ValidateProvider (int providerID){};
   boolean ValidateMember(int memberID){};

    Report requestProviderReport(){};
    int lookupServiceCode(String name){
        //this creates a service object and finds the relevant code
    };

    void CreateServiceRecord(){//creates a service record object};



};