/*
 * Parker Jones
 * 11/09/17
 * Database Controller
 */

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.*;

public class DatabaseController {

    private static Path membersPath = Paths.get("./data/members.txt");
    private static Path providersPath = Paths.get("./data/providers.txt"); // double check
    private static Charset charset = StandardCharsets.UTF_8;


    static ArrayList<Member> members = new ArrayList<Member>();
    static ArrayList<Provider> providers = new ArrayList<Provider>();
    private static ArrayList<ServiceRecord> serviceRecords = new ArrayList<ServiceRecord>();
    private static ArrayList<ServiceCode> serviceCodes = new ArrayList<ServiceCode>();

    private static File memberIn = new File("./data/members.txt");
    private static File providerIn = new File("./data/providers.txt");


    static void setup() {

        System.out.println("Setting up database...");

        System.out.println("Checking for member data...");



        if(memberIn.exists()) {
            System.out.println("Member Data exists. Reading data...");


            try{
                List<String> lines = Files.readAllLines(Paths.get("./data/members.txt"));

                //parses the string for the information then adds it to the members ArrayList
                for(int x = 0; x < lines.size(); x++) {
                    String[] parsedMemberData = lines.get(x).split("[|]");
                    for (int y = 0; y < parsedMemberData.length; y++) {
                        if( parsedMemberData[y].charAt(0) == ' ') {
                            parsedMemberData[y] = parsedMemberData[y].substring(1, parsedMemberData[y].length());
                        }
                        if(parsedMemberData[y].charAt(parsedMemberData[y].length()-1) == ' ') {
                            parsedMemberData[y] = parsedMemberData[y].substring(0, parsedMemberData[y].length()-1);
                        }
                    }
                    String firstName = parsedMemberData[0];
                    String lastName = parsedMemberData[1];
                    String address = parsedMemberData[2];
                    String city = parsedMemberData[3];
                    String state = parsedMemberData[4];
                    String zip = parsedMemberData[5];
                    String email = parsedMemberData[6];
                    String phone = parsedMemberData[7];
                    int id = Integer.parseInt(parsedMemberData[8]);
                    Member newMember = new Member(firstName, lastName, address, city, state, zip, email, phone, id);
                    members.add(newMember);
                    System.out.println(parsedMemberData[9]);
                    if(parsedMemberData[9].equals("Suspended")) {  newMember.suspend(); }

                }

                System.out.println(members.size() + " members loaded from file.");

            }
            catch (IOException e) {
                System.out.println("Failed to read from file. Starting with empty member list. " + e);
            }
        }
        else {
            System.out.println("Member Data file doesn't exist. Creating...");

            try {
                if(!memberIn.createNewFile()) System.out.println("ERROR: COULD NOT CREATE MEMBER DATA FILE");
            }
            catch (IOException e) {
                System.out.println("Tried creating file but file already exists! : " + e);
            }
        }


        if(providerIn.exists()) {
            System.out.println("Member Data exists. Reading data...");

            try{
                List<String> providerLines = Files.readAllLines(Paths.get("./data/providers.txt"));

                //parses the string for the information then adds it to the members ArrayList
                for(int x = 0; x < providerLines.size(); x++) {
                    String[] parsedProviderData = providerLines.get(x).split("[|]");
                    for (int y = 0; y < parsedProviderData.length; y++) {
                        if(parsedProviderData[y].charAt(0) == ' ') {
                            parsedProviderData[y] = parsedProviderData[y].substring(1, parsedProviderData[y].length());
                        }
                        if(parsedProviderData[y].charAt(parsedProviderData[y].length()-1) == ' ') {
                            parsedProviderData[y] = parsedProviderData[y].substring(0, parsedProviderData[y].length()-1);
                        }
                    }
                    String firstName = parsedProviderData[0];
                    String lastName = parsedProviderData[1];
                    String address = parsedProviderData[2];
                    String city = parsedProviderData[3];
                    String state = parsedProviderData[4];
                    String zip = parsedProviderData[5];
                    String email = parsedProviderData[6];
                    String phone = parsedProviderData[7];
                    int id = Integer.parseInt(parsedProviderData[8]);
                    providers.add(new Provider(firstName, lastName, address, city, state, zip, email, phone, id));
                }

                System.out.println(providers.size() + " providers loaded from file.");

            }
            catch (IOException e) {
                System.out.println("Failed to read from file. Starting with empty provider list. " + e);
            }
        }
        else {
            System.out.println("Provider Data file doesn't exist. Creating...");

            try {
                if(!providerIn.createNewFile()) System.out.println("ERROR: COULD NOT CREATE PROVIDERS DATA FILE");
            }
            catch (IOException e) {
                System.out.println("Tried creating file but file already exists! : " + e);
            }
        }
    }


    //creates another member in the list
    static Member newMember(String firstName, String lastName, String streetAddress,
                          String city, String state, String zipCode,String email,
                          String phoneNumber) {

        int newUserID = 100000000;

        if(members.size() > 0) {
            newUserID += members.size();
        }

        Member newMember = new Member(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, newUserID);

        members.add(newMember);

        try(FileWriter fw = new FileWriter("./data/members.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            System.out.println("Writing \n" + newMember.toString() + "\nto file");
            out.print("\n" + newMember.toString());

        } catch (IOException e) {
            System.out.println("ERROR: FAILED TO WRITE NEW MEMBER TO FILE " + e);
        }

        return newMember;
    }


    //creates a new provider in the list
    static Provider newProvider(String firstName, String lastName, String streetAddress,
                            String city, String state, String zipCode,String email,
                            String phoneNumber) {

        int newProviderID = 999999999;

        if(providers.size() > 0) {
            newProviderID -= providers.size();
        }

        Provider provider = new Provider(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, newProviderID);

        providers.add(provider);

        return provider;
    }



    //deletes the member with the corresponding ID
    public static void deleteMember(int userID) {
        Member tempMember = getMember(userID);


        members.remove(tempMember);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(tempMember.getFirstName() + " | " + tempMember.getLastName() + " | " + tempMember.getStreetAddress() +
                            " | " + tempMember.getCity() + " | " + tempMember.getState() + " | " + tempMember.getZipCode() +
                            " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() + " | (.*);" , "");
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new first name" + e);
        }
    }

    public static void deleteProvider(int userID) {
        Provider tempProvider = getProvider(userID);


        providers.remove(tempProvider);

        try {
            String content = new String(Files.readAllBytes(providersPath), charset);
            content = content.replace(tempProvider.getFirstName() + " | " + tempProvider.getLastName() + " | " + tempProvider.getStreetAddress() +
                            " | " + tempProvider.getCity() + " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() +
                            " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() + " | (.*);" , "");
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update Provider file with new first name" + e);
        }
    }


    //suspends the member with the corresponding ID
    static void suspendMember(int userID) {
        getMember(userID).suspend();


        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace( Integer.toString(userID).concat(" | Active;")  , Integer.toString(userID).concat(" | Suspended;")  );
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with suspension. " + e);
        }
    }


    //reactivates the member with the corresponding ID
    static void reactivateMember(int userID) {
        getMember(userID).setActive();

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace( Integer.toString(userID).concat(" | Suspended;")  , Integer.toString(userID).concat(" | Active;")  );
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with ReActivation. " + e);
        }
    }


    //returns the Member object of the member with the corresponding ID
    static Member getMember(int userID) {
        for (Member member : members) {
            if (member.getUserID() == userID) {
                return member;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Member("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", -1);
    }


    //returns the index of the member
    private static int getMemberIndex(int userID) {

        for (int x = 0; x < members.size(); x++) {
            if (members.get(x).getUserID() == userID) {
                return x;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return -1;
    }


    public static Provider validateProvider(int providerID) {

        Provider returnedProvider = getProvider(providerID);

        if(returnedProvider.getName().equals("-1")) {
            return null;
        }
        else return returnedProvider;

    }


    //returns the Provider object of the provider with the correspondin ID
    static Provider getProvider(int userID) {
        for (Provider provider : providers) {
            if (provider.getUserID() == userID) {
                return provider;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Provider("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", -1);
    }


    //returns the index of the Provider
    private static int getProviderIndex (int userID) {

        for (int x = 0; x < providers.size(); x++) {
            if (providers.get(x).getUserID() == userID) {
                return x;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return -1;
    }


    //creates a new Service Code with the next available ID
    static void addServiceCode(String name, double fee) {

        int newCode = 100000;

        if(serviceCodes.size() > 0) {
            newCode += serviceCodes.size();
        }

        serviceCodes.add(new ServiceCode(name, newCode, fee));

    }


    //search the service codes by name
    static ServiceCode searchServiceCodes(String name) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceName.equals(name)) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    //search the service codes by the code
    static ServiceCode searchServiceCodes(int code) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceCode == code) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    //search the service records by member
    static ServiceRecord[] searchServiceRecords(Member member) {

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.MemberNumber == member.getUserID() ) {
                tempRecords.add(record);
            }
        }

        return tempRecords.toArray(new ServiceRecord[tempRecords.size()]);
    }


    //search the service records by provider
    static ServiceRecord[] searchServiceRecords(Provider provider){

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.ProviderNumber == provider.getUserID() ) {
                tempRecords.add(record);
            }
        }

        return tempRecords.toArray(new ServiceRecord[tempRecords.size()]);

    }


    //updates the members first name
    static void updateMemberFirstName(int memberID, String newFirstName) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(tempMember.getFirstName() + " | " + tempMember.getLastName() + " | " + tempMember.getStreetAddress() +
                            " | " + tempMember.getCity() + " | " + tempMember.getState() + " | " + tempMember.getZipCode() +
                            " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() ,

                    newFirstName + " | " + tempMember.getLastName() + " | " + tempMember.getStreetAddress() +
                            " | " + tempMember.getCity() + " | " + tempMember.getState() + " | " + tempMember.getZipCode() +
                            " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID());
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new first name" + e);
        }

        members.get(getMemberIndex(memberID)).setFirstName(newFirstName);

    }


    //updates the members last name
    static void updateMemberLastName(int memberID, String newLastName) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(" | " + tempMember.getLastName() + " | " + tempMember.getStreetAddress() +
                            " | " + tempMember.getCity() + " | " + tempMember.getState() + " | " + tempMember.getZipCode() +
                            " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() ,

                    " | " + newLastName + " | " + tempMember.getStreetAddress() +
                            " | " + tempMember.getCity() + " | " + tempMember.getState() + " | " + tempMember.getZipCode() +
                            " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID());
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new last name" + e);
        }

        members.get(getMemberIndex(memberID)).setLastName(newLastName);
    }


    //updates the members address
    static void updateMemberAddress(int memberID, String newAddress) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(" | " + tempMember.getStreetAddress() + " | " + tempMember.getCity() + " | "
                            + tempMember.getState() + " | " + tempMember.getZipCode() + " | " + tempMember.getEmail() +
                            " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() ,
                    " | " + newAddress + " | " + tempMember.getCity() + " | "
                            + tempMember.getState() + " | " + tempMember.getZipCode() + " | " + tempMember.getEmail() +
                            " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID());
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new address" + e);
        }

        members.get(getMemberIndex(memberID)).setStreetAddress(newAddress);
    }


    //updates the members city
    static void updateMemberCity(int memberID, String newCity) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(" | " + tempMember.getCity() + " | " + tempMember.getState() + " | " + tempMember.getZipCode() + " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() ,
                    " | " + newCity + " | " + tempMember.getState() + " | " + tempMember.getZipCode() + " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID());
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new city" + e);
        }
        members.get(getMemberIndex(memberID)).setCity(newCity);
    }


    //updates the members state
    static void updateMemberState(int memberID, String newState) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(   " | " + tempMember.getState() + " | " + tempMember.getZipCode() + " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() ,
                    " | " + newState + " | " + tempMember.getZipCode() + " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID());
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new state" + e);
        }
        members.get(getMemberIndex(memberID)).setState(newState);
    }


    //updates the members zip code
    static void updateMemberZip(int memberID, String newZip) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(   " | " + tempMember.getZipCode() + " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() ,
                    " | " + newZip + " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID());
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new zip code" + e);
        }
        members.get(getMemberIndex(memberID)).setZipCode(newZip);
    }


    //updates the members phone number
    static void updateMemberPhone(int memberID, String newPhone) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(  " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() , " | " + newPhone +" | " + tempMember.getUserID()  );
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new phone number" + e);
        }
        members.get(getMemberIndex(memberID)).setPhoneNumber(newPhone);
    }


    //updates the members email
    static void updateMemberEmail(int memberID, String newEmail) {


        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            System.out.println("|" + tempMember.getEmail() + "|");
            content = content.replace(  " | " + tempMember.getEmail() + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() , " | " + newEmail + " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID());
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new email address" + e);
        }

        members.get(getMemberIndex(memberID)).setEmail(newEmail);
    }


    //updates the provider's first name
    public static void updateProviderFirstName(int providerID, String newFirstName) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(providersPath), charset);
            content = content.replace(tempProvider.getFirstName() + " | " + tempProvider.getLastName() + " | " + tempProvider.getStreetAddress() +
                            " | " + tempProvider.getCity() + " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() +
                            " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() ,

                    newFirstName + " | " + tempProvider.getLastName() + " | " + tempProvider.getStreetAddress() +
                            " | " + tempProvider.getCity() + " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() +
                            " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID());
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new first name" + e);
        }
        tempProvider.setZipCode(newFirstName); // why 

        providers.get(getProviderIndex(providerID)).setFirstName(newFirstName);
    }

    
    //updates the provider's last name
    static void updateProviderLastName(int providerID, String newLastName) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(providersPath), charset);
            content = content.replace(" | " + tempProvider.getLastName() + " | " + tempProvider.getStreetAddress() +
                            " | " + tempProvider.getCity() + " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() +
                            " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() ,

                    " | " + newLastName + " | " + tempProvider.getStreetAddress() +
                            " | " + tempProvider.getCity() + " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() +
                            " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID());
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new last name" + e);
        }

        providers.get(getProviderIndex(providerID)).setLastName(newLastName);
    }


    //updates the provider's address
    static void updateProviderAddress(int providerID, String newAddress) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(providersPath), charset);
            content = content.replace(" | " + tempProvider.getStreetAddress() + " | " + tempProvider.getCity() + " | "
                            + tempProvider.getState() + " | " + tempProvider.getZipCode() + " | " + tempProvider.getEmail() +
                            " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() ,
                    " | " + newAddress + " | " + tempProvider.getCity() + " | "
                            + tempProvider.getState() + " | " + tempProvider.getZipCode() + " | " + tempProvider.getEmail() +
                            " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID());
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new address" + e);
        }

        providers.get(getProviderIndex(providerID)).setStreetAddress(newAddress);
    }


    //updates the provider's city
    static void updateProviderCity(int providerID, String newCity) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(providersPath), charset);
            content = content.replace(" | " + tempProvider.getCity() + " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() + " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() ,
                    " | " + newCity + " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() + " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID());
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new city" + e);
        }
        providers.get(getProviderIndex(providerID)).setCity(newCity);
    }


    //updates the providers state
    static void updateProviderState(int providerID, String newState) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(providersPath), charset);
            content = content.replace(   " | " + tempProvider.getState() + " | " + tempProvider.getZipCode() + " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() ,
                    " | " + newState + " | " + tempProvider.getZipCode() + " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID());
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new state" + e);
        }
        providers.get(getProviderIndex(providerID)).setState(newState);
    }


    //updates the members zip code
    static void updateProviderZip(int providerID, String newZip) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(   " | " + tempProvider.getZipCode() + " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() ,
                    " | " + newZip + " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID());
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new zip code" + e);
        }
        providers.get(getProviderIndex(providerID)).setZipCode(newZip);
    }


    //updates the members phone number
    static void updateProviderPhone(int providerID, String newPhone) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(  " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() , " | " + newPhone +" | " + tempProvider.getUserID()  );
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new phone number" + e);
        }
        providers.get(getProviderIndex(providerID)).setPhoneNumber(newPhone);
    }


    //updates the members email
    static void updateProviderEmail(int providerID, String newEmail) {

        Provider tempProvider = getProvider(providerID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            System.out.println("|" + tempProvider.getEmail() + "|");
            content = content.replace(  " | " + tempProvider.getEmail() + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID() , " | " + newEmail + " | " + tempProvider.getPhoneNumber() + " | " + tempProvider.getUserID());
            Files.write(providersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update provider file with new email address" + e);
        }

        providers.get(getProviderIndex(providerID)).setEmail(newEmail);
    }



}