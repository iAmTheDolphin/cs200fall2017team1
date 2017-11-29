/*
 * Parker Jones
 * 11/09/17
 * Database Controller
 */

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Files.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.*;

public class DatabaseController {

    static Path membersPath = Paths.get("./data/members.txt");
    static Charset charset = StandardCharsets.UTF_8;


    static ArrayList<Member> members = new ArrayList<Member>();
    static ArrayList<Provider> providers = new ArrayList<Provider>();
    static ArrayList<ServiceRecord> serviceRecords = new ArrayList<ServiceRecord>();
    static ArrayList<ServiceCode> serviceCodes = new ArrayList<ServiceCode>();

    private static File memberIn = new File("./data/members.txt");
    private static File providerIn = new File("./data/providers.txt");

    public static void setup() {

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
                memberIn.createNewFile();
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
                providerIn.createNewFile();
            }
            catch (IOException e) {
                System.out.println("Tried creating file but file already exists! : " + e);
            }
        }
    }



    //creates another member in the list
    public static Member newMember(String firstName, String lastName, String streetAddress,
                          String city, String state, String zipCode,String email,
                          String phoneNumber) {

        int newUserID = 100000000;

        if(members.size() > 0) {
            newUserID += members.size();
        }

        Member newMember = new Member(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, newUserID);

        members.add(newMember);

        Path path = Paths.get("./data/members.txt");
        Charset charset = StandardCharsets.UTF_8;

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
    public static Provider newProvider(String firstName, String lastName, String streetAddress,
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
        members.remove(getMember(userID));
    }


    //suspends the member with the corresponding ID
    public static void suspendMember(int userID) {
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
    public static void reactivateMember(int userID) {
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
    public static Member getMember(int userID) {
        for (Member member : members) {
            if (member.getUserID() == userID) {
                return member;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Member("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", -1);
    }


    //returns the Provider object of the provider with the correspondin ID
    public static Provider getProvider(int userID) {
        for (Provider provider : providers) {
            if (provider.getUserID() == userID) {
                return provider;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Provider("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", -1);
    }


    //creates a new Service Code with the next available ID
    public static void addServiceCode(String name, double fee) {

        int newCode = 100000;

        if(serviceCodes.size() > 0) {
            newCode += serviceCodes.size();
        }

        serviceCodes.add(new ServiceCode(name, newCode, fee));

    }


    //search the service codes by name
    public static ServiceCode searchServiceCodes(String name) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceName.equals(name)) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    //search the service codes by the code
    public static ServiceCode searchServiceCodes(int code) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceCode == code) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    //search the service records by member
    public static ServiceRecord[] searchServiceRecords(Member member) {

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.MemberNumber == member.getUserID() ) {
                tempRecords.add(record);
            }
        }

        ServiceRecord[] matchingRecords = tempRecords.toArray(new ServiceRecord[tempRecords.size()]);

        return matchingRecords;
    }


    //search the service records by provider
    public static ServiceRecord[] searchServiceRecords(Provider provider){

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.ProviderNumber == provider.getUserID() ) {
                tempRecords.add(record);
            }
        }

        ServiceRecord[] matchingRecords = tempRecords.toArray(new ServiceRecord[tempRecords.size()]);

        return matchingRecords;

    }

    //generates a service record and adds it to the list as well as writes it to file
    //public static ServiceRecord generateServiceRecord() { }


    //search the service records by Time period TODO this is Incomplete
    public static void searchServiceRecords(Date start, Date end) {



    }


    public static void createMemberRecord(Member member) {

        MemberReport report = new MemberReport(member);

    }


    public static void updateMemberFirstName(int memberID, String newFirstName) {

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
        tempMember.setZipCode(newFirstName);
    }

    public static void updateMemberLastName(int memberID, String newLastName) {

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
        tempMember.setZipCode(newLastName);
    }

    public static void updateMemberAddress(int memberID, String newAddress) {

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
        tempMember.setZipCode(newAddress);
    }

    public static void updateMemberCity(int memberID, String newCity) {

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
        tempMember.setZipCode(newCity);
    }

    public static void updateMemberState(int memberID, String newState) {

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
        tempMember.setZipCode(newState);
    }

    public static void updateMemberZip(int memberID, String newZip) {

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
        tempMember.setZipCode(newZip);
    }

    public static void updateMemberPhone(int memberID, String newPhone) {

        Member tempMember = getMember(memberID);

        try {
            String content = new String(Files.readAllBytes(membersPath), charset);
            content = content.replace(  " | " + tempMember.getPhoneNumber() + " | " + tempMember.getUserID() , " | " + newPhone +" | " + tempMember.getUserID()  );
            Files.write(membersPath, content.getBytes(charset));
        }
        catch(IOException e) {
            System.out.println("ERROR: Could not update member file with new phone number" + e);
        }
        tempMember.setPhoneNumber(newPhone);
    }

    public static void updateMemberEmail(int memberID, String newEmail) {


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

        tempMember.setEmail(newEmail);

    }

}