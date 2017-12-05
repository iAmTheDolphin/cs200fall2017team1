public class ServiceRecordUtility {//Note to self: go back and fix this. it is redundant.

    public static ServiceCode FindServiceInfo(String ServiceName, boolean check) {
        ServiceCode y = DatabaseController.searchServiceCodes(ServiceName);
        if (check){
            if (y.serviceName == "-1"){
                System.out.println("\nThis isn't a valid name. Try again? (Y/N)");
                ProviderInterface tempx = new ProviderInterface();
                char input = tempx.TryAgain();
                if (input == 'y' || input == 'Y'){

                        System.out.println("\nPlease try re-entering the name.");
                        String temp = scan.nextLine();
                        FindServiceInfo(temp, true);
                }
                else {
                    System.out.println("You cannot make a service record without this information. Returning to main menu.");
                    ProviderInterface temp  = new ProviderInterface();
                    temp.MainMenu();
                }
            }
            if (y.serviceFee == -1){System.out.println("\nThis should not happen. If it does, fix later.");}
        }

        if (y.serviceFee == -1) {
            System.out.println("\nIs this a new service? (Y/N)");
            ProviderInterface tempx = new ProviderInterface();
            char input = tempx.TryAgain();
            if (input == 'y' || input == 'Y') {
                double fee = 0.0;

                System.out.println("\nWhat is the fee for " + ServiceName + "?");
                fee = scan.nextDouble();

                ServiceCode temp = new ServiceCode(ServiceName, 0, fee);
                temp.AddService();
                y = DatabaseController.searchServiceCodes(ServiceName);
            } else {
                System.out.println("\nPlease try entering the name again: ");
                String tempp = scan.nextLine();
                FindServiceInfo(tempp, true);
            }

        }
        System.out.println("\nThe fee for this service is : $" + y.serviceFee + ". Is this correct?(Y/N)");
        ProviderInterface tempy = new ProviderInterface();
        char input = tempy.TryAgain();
        if (input == 'y' || input == 'Y') {
            return y;
        } else {
            System.out.println("\nPlease try re-entering the service name. ");
            String tempp = scan.nextLine();
            FindServiceInfo(tempp, false);
        }

        return y;

    }

    public static ServiceCode FindServiceInfo(int ServiceCode, boolean check) {
        ServiceCode y = DatabaseController.searchServiceCodes(ServiceCode);
        if (check) {
            if (y.serviceFee == -1) {
                System.out.println("\nThis service does not exist. Try again? (Y/N)");
                ProviderInterface tempx = new ProviderInterface();
                char input = tempx.TryAgain();
                if (input == 'y' || input == 'Y') {
                        System.out.println("\nPlease try re-entering the name.");
                        String temp = scan.nextLine();
                        FindServiceInfo(temp, true);
                    }
                else {
                    System.out.println("You cannot make a service record without this information. Returning to main menu.");
                    ProviderInterface temp  = new ProviderInterface();
                    temp.MainMenu();
                }
                }
            }

        if (y.serviceFee == -1) {
            System.out.println("\nIs this a new service? (Y/N)");
            ProviderInterface tempy = new ProviderInterface();
            char input = tempy.TryAgain();
            if (input == 'y' || input == 'Y') {
                System.out.println("\nKeep in mind that this new service may have a different code than the one you entered.");
                double fee = 0.0;
                String ServiceName;
                System.out.println("\nWhat is the name of this service? ");
                ServiceName = scan.nextLine();
                System.out.println("\nWhat is the fee for " + ServiceName + "?");
                fee = scan.nextDouble();

                ServiceCode temp = new ServiceCode(ServiceName, 0, fee);
                temp.AddService();
                y = temp;
                return y;
            } else {
                System.out.println("\nPlease try entering the code again: ");
                int tempp = scan.nextInt();
                FindServiceInfo(tempp, true);//TODO change this to its proper value. I just changed it to false to stop errors -P
            }

        }

        System.out.println("\nThe name of this service is :" + y.serviceName + ". Is this correct? (Y/N)");
        ProviderInterface tempy = new ProviderInterface();
        char input = tempy.TryAgain();
        if (input == 'y' || input == 'Y') {
            return y;
        } else {
            System.out.println("\nPlease try re-entering the service code. ");
            int tempp = scan.nextInt();
            FindServiceInfo(tempp, true);//TODO change this to its proper value. I just changed it to false to stop errors -P
        }
        return y;


    }


}
