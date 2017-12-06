/*
 * Parker Jones
 * 11/09/17
 * Database Controller
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.*;

/**
 * This class maintains and controlls access to the data
 *
 * @author Parker Jones
 * @version 1.0
 *
 */

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
    private static File serviceCodesIn = new File("./data/serviceCodes.txt");
    private static File serviceRecordsIn = new File("./data/serviceRecords.txt");


    /**
     * This method sets up the database initially by importing the data from the files
     */
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
                    if(parsedMemberData[9].equals("Suspended;")) {  newMember.suspend(); }

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
            System.out.println("Provider Data exists. Reading data...");

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



        if(serviceCodesIn.exists()) {
            System.out.println("Service Code Data exists. Reading data...");


            try{
                List<String> lines = Files.readAllLines(Paths.get("./data/serviceCodes.txt"));

                //parses the string for the information then adds it to the members ArrayList
                for(int x = 0; x < lines.size(); x++) {
                    String[] parsedServiceData = lines.get(x).split("[|]");
                    for (int y = 0; y < parsedServiceData.length; y++) {
                        if( parsedServiceData[y].charAt(0) == ' ') {
                            parsedServiceData[y] = parsedServiceData[y].substring(1, parsedServiceData[y].length());
                        }
                        if(parsedServiceData[y].charAt(parsedServiceData[y].length()-1) == ' ') {
                            parsedServiceData[y] = parsedServiceData[y].substring(0, parsedServiceData[y].length()-1);
                        }
                    }
                    String serviceName = parsedServiceData[0];
                    int serviceCode = Integer.parseInt(parsedServiceData[1]);
                    Double fee = Double.parseDouble( parsedServiceData[2]);

                    ServiceCode newService = new ServiceCode(serviceName, serviceCode, fee);
                    serviceCodes.add(newService);
                }

                System.out.println(serviceCodes.size() + " services loaded from file.");

            }
            catch (IOException e) {
                System.out.println("Failed to read from file. Starting with empty service list. " + e);
            }
        }
        else {
            System.out.println("Service Data file doesn't exist. Creating...");

            try {
                if(!serviceCodesIn.createNewFile()) System.out.println("ERROR: COULD NOT CREATE SERVICE CODE DATA FILE");
            }
            catch (IOException e) {
                System.out.println("Tried creating file but file already exists! : " + e);
            }
        }


        if(serviceRecordsIn.exists()) {
            System.out.println("Service Record Data exists. Reading data...");
            SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);


            try{
                List<String> lines = Files.readAllLines(Paths.get("./data/serviceRecords.txt"));

                //parses the string for the information then adds it to the members ArrayList
                for(int x = 0; x < lines.size(); x++) {
                    String[] parsedServiceRecord = lines.get(x).split("[|]");
                    for (int y = 0; y < parsedServiceRecord.length; y++) {
                        if( parsedServiceRecord[y].charAt(0) == ' ') {
                            parsedServiceRecord[y] = parsedServiceRecord[y].substring(1, parsedServiceRecord[y].length());
                        }
                        if(parsedServiceRecord[y].charAt(parsedServiceRecord[y].length()-1) == ' ') {
                            parsedServiceRecord[y] = parsedServiceRecord[y].substring(0, parsedServiceRecord[y].length()-1);
                        }
                    }
                    String providerName = parsedServiceRecord[0];
                    int providerID = Integer.parseInt(parsedServiceRecord[1]);
                    String memberName = parsedServiceRecord[2];
                    int memberID = Integer.parseInt(parsedServiceRecord[3]);
                    String serviceName = parsedServiceRecord[4];
                    int serviceCode = Integer.parseInt(parsedServiceRecord[5]);
                    Double fee = Double.parseDouble(parsedServiceRecord[6]);
                    String notes = parsedServiceRecord[9];
                    Date serviceDate;
                    Date currentDate;
                    try{
                        serviceDate = parserSDF.parse(parsedServiceRecord[7]);
                        currentDate = parserSDF.parse(parsedServiceRecord[8]);
                        ServiceCode service = DatabaseController.searchServiceCodes(serviceCode);
                        ServiceRecord newServiceRecord = new ServiceRecord(providerID, memberID, providerName, memberName, notes, serviceDate, currentDate, service);
                        serviceRecords.add(newServiceRecord);
                    }
                    catch(ParseException e) {
                        System.out.println("ERROR PARSING SERVICE RECORD TIMESTAMPS. LOADING NEXT ONE");
                    }
                }

                System.out.println(serviceRecords.size() + " services records loaded from file.");

            }
            catch (IOException e) {
                System.out.println("Failed to read from file. Starting with empty service record list. " + e);
            }
        }
        else {
            System.out.println("Service Record file doesn't exist. Creating...");

            try {
                if(!serviceRecordsIn.createNewFile()) System.out.println("ERROR: COULD NOT CREATE SERVICE RECORD DATA FILE");
            }
            catch (IOException e) {
                System.out.println("Tried creating file but file already exists! : " + e);
            }
        }
    }



    /**
     *  This method creates a new member object in the member arrayList and adds them to the data file
     * @param firstName The member's first name
     * @param lastName The member's last name
     * @param streetAddress Member's street address
     * @param city Member's city
     * @param state Member's state
     * @param zipCode Member's zip code
     * @param email Member's email
     * @param phoneNumber Member's phone number
     * @return Member
     */
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


    /**
     * This method creates a new Provider object in the provider arrayList and adds them to the data file
     * @param firstName Provider's first name
     * @param lastName Provider's last name
     * @param streetAddress Provider's street address
     * @param city Provider's city
     * @param state Provider's state
     * @param zipCode Provider's zip code
     * @param email Provider's email
     * @param phoneNumber Provider's phone number
     * @return Provider
     */
    static Provider newProvider(String firstName, String lastName, String streetAddress,
                            String city, String state, String zipCode,String email,
                            String phoneNumber) {

        int newProviderID = 999999999;

        if(providers.size() > 0) {
            newProviderID -= providers.size();
        }



        Provider provider = new Provider(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, newProviderID);

        providers.add(provider);

        try(FileWriter fw = new FileWriter("./data/providers.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            System.out.println("Writing \n" + provider.toString() + "\nto file");
            out.print("\n" + provider.toString());

        } catch (IOException e) {
            System.out.println("ERROR: FAILED TO WRITE NEW PROVIDER TO FILE " + e);
        }

        return provider;
    }


    /**
     * This method deletes a Member from the arrayList and the data file
     * @param userID The User's identification number
     */
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


    /**
     * this method deletes a Provider from the arrayList and the data file
     * @param userID The User's identification number
     */
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


    /**
     * This method suspends the member with the given ID
     * @param userID The User's identification number
     */
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


    /**
     * this method reactivates the member with the corresponding ID
     * @param userID The User's identification number
     */
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


    /**
     * returns the member object represented by the ID
     * @param userID The User's identification number
     * @return Member
     */
    static Member getMember(int userID) {
        for (Member member : members) {
            if (member.getUserID() == userID) {
                return member;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Member("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", -1);
    }


    /**
     * returns the index of the Member with the given ID in the ArrayList
     * @param userID The User's identification number
     * @return int
     */
    private static int getMemberIndex(int userID) {

        for (int x = 0; x < members.size(); x++) {
            if (members.get(x).getUserID() == userID) {
                return x;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return -1;
    }


    /**
     * returns the provider Object associated with the given ID
     * @param userID The User's identification number
     * @return Provider
     */
    static Provider getProvider(int userID) {
        for (Provider provider : providers) {
            if (provider.getUserID() == userID) {
                return provider;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Provider("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", -1);
    }


    /**
     * returns the index of the provider with the given ID
     * @param userID The User's identification number
     * @return int
     */
    private static int getProviderIndex (int userID) {

        for (int x = 0; x < providers.size(); x++) {
            if (providers.get(x).getUserID() == userID) {
                return x;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return -1;
    }


    /**
     * adds a service code to the arrayList and to the data file
     * @param name The name of the service
     * @param fee The fee for the service
     */
    static void addServiceCode(String name, double fee) {

        int newCode = 100000;

        if(serviceCodes.size() > 0) {
            newCode += serviceCodes.size();
        }

        ServiceCode serviceCode = new ServiceCode(name, newCode, fee);

        serviceCodes.add(serviceCode);

        try(FileWriter fw = new FileWriter("./data/serviceCodes.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            System.out.println("Writing \n" + serviceCode.toString() + "\nto file");
            out.print("\n" + serviceCode.toString());

        } catch (IOException e) {
            System.out.println("ERROR: FAILED TO WRITE NEW SERVICE CODE TO FILE " + e);
        }
    }


    /**
     * searches for a serviceCode by name
     * @param name The name of the service
     * @return ServiceCode
     */
    static ServiceCode searchServiceCodes(String name) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceName.toLowerCase().equals(name.toLowerCase())) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    /**
     * search the ServiceCodes by the actual service code
     * @param code The numeric code assigned to a service
     * @return
     */
    static ServiceCode searchServiceCodes(int code) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceCode == code) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    /**
     * displays all the service codes
     */
    static void displayServiceCodes() {

        System.out.println("\n\nProvider Directory\n\n");

        for (ServiceCode serviceCode : serviceCodes) {

            System.out.println ("\nName: " + serviceCode.serviceName + " || ");
            System.out.println ("Code: " + serviceCode.serviceCode + " || ");
            System.out.println ("Fee: $" + serviceCode.serviceFee + " || ");

        }

    }


    /**
     * this searches the service records by the member associated with that record
     * @param member The member associated to the service record
     * @return
     */
    static ServiceRecord[] searchServiceRecords(Member member) {

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.MemberNumber == member.getUserID() ) {
                tempRecords.add(record);
            }
        }

        return tempRecords.toArray(new ServiceRecord[tempRecords.size()]);
    }


    /**
     * this searches the service records by the provider associated with that record
     * @param provider The provider associated to the service record
     * @return
     */
    static ServiceRecord[] searchServiceRecords(Provider provider){

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.ProviderNumber == provider.getUserID() ) {
                tempRecords.add(record);
            }
        }

        return tempRecords.toArray(new ServiceRecord[tempRecords.size()]);

    }

    /**
     * adds a service record to the arrayList
     * @param record The service record being added
     */
    static void addServiceRecord(ServiceRecord record) {
        serviceRecords.add(record);
        try(FileWriter fw = new FileWriter("./data/serviceRecords.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            System.out.println("Writing \n" + record.toString() + "\nto file");
            out.print("\n" + record.toFileString());

        } catch (IOException e) {
            System.out.println("ERROR: FAILED TO WRITE NEW SERVICE CODE TO FILE " + e);
        }
    }

    /**
     * this updates the corresponding members first name in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newFirstName The Member's new first name
     */
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

    /**
     * updates the corresponding member's last name in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newLastName The Member's updated last name
     */
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


    /**
     * updates the corresponding member's address in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newAddress Member's updated address
     */
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


    /**
     * updates the corresponding member's city in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newCity Member's updated city
     */
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


    /**
     * this updates the corresponding member's State in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newState Member's updated state
     */
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


    /**
     * this updates the corresponding member's zip code in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newZip Member's updated zip
     */
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


    /**
     * this updates the corresponding member's phone number in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newPhone Member's updated phone
     */
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


    /**
     * this updates the corresponding member's email in the arrayList and the data file
     * @param memberID The Member's identification number
     * @param newEmail Member's updated email
     */
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


    /**
     * this updates the corresponding provider's first name in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newFirstName Provider's updated first name
     */
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
        tempProvider.setFirstName(newFirstName);

        providers.get(getProviderIndex(providerID)).setFirstName(newFirstName);
    }


    /**
     * this updates the corresponding provider's first name in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newLastName Provider's updated last name
     */
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


    /**
     * this updates the corresponding provider's address in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newAddress Provider's updated address
     */
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


    /**
     * this updates the corresponding provider's city in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newCity Provider's updated city
     */
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


    /**
     * this updates the corresponding provider's state in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newState Provider's updated state
     */
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


    /**
     * this updates the corresponding provider's zip code in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newZip Provider's updated zip
     */
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


    /**
     * this updates the corresponding provider's phone number in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newPhone Provider's updated phone
     */
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


    /**
     * this updates the corresponding provider's email in the arrayList and the data file
     * @param providerID The Provider's identification number
     * @param newEmail Provider's updated email 
     */
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