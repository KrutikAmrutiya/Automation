package helpers;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class JavaHelper extends SeleniumHelper{

	// Generate Random Number
    public String generateRandomNumber(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10)); // Appends a random digit (0-9)
        }
        return number.toString();
    }

    // Generate Random Alphabets
    public String generateRandomAlphabets(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder alphabets = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a'); // Random lowercase letter
            alphabets.append(c);
        }
        return alphabets.toString();
    }

    // Generate Random Gmail Address
    public String generateRandomGmail(int nameLength) {
        String randomName = generateRandomAlphabets(nameLength);
        String randomNumber = generateRandomNumber(4); // Append 4-digit random number for uniqueness
        return randomName + randomNumber + "@gmail.com";
    }

    // Generate Random Chat Name
    public String generateRandomChatName(int nameLength, int numberLength) {
        String randomNamePart = generateRandomAlphabets(nameLength);
        String randomNumberPart = generateRandomNumber(numberLength);
        return randomNamePart + randomNumberPart;
    }

    // Generate Fixed Prefix Random Number
    public String generateFixedPrefixRandomNumber(String prefix, int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10)); // Append random digit (0-9)
        }
        return prefix + number.toString();
    }

    // Decode and Send
    public String decodeAndSend(String encodedValue) {
        byte[] decodedString = Base64.decodeBase64(encodedValue);
        return new String(decodedString);
    }

    // Get Dynamic Locator
    public String getDynamicLocator(String baseLocator, String dynamicValue) {
        return baseLocator.replace("{{dynamicValue}}", dynamicValue);
    }
    
    public void NormalFixedWait() throws Exception
    {
    	Thread.sleep(4000);
    }
    
    public void MediumFixedWait() throws Exception
    {
    	Thread.sleep(10000);
    }
    
    public void HighFixedWait() throws Exception
    {
    	Thread.sleep(30000);
    }
}
