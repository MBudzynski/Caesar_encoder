package sample;

public class Encryptor {

    private final int firstCharacter = 32;
    private final int lastCharacter = 126;


    private int shift = 0;

    public void setShift(int shift) {
        this.shift = shift;
    }


    public String encodeText(String text) {
        char[] encodedChars = text.toCharArray();
        for (int i = 0; i < encodedChars.length; i++) {
            if((encodedChars[i] + shift) > lastCharacter){
                encodedChars[i] = (char)(firstCharacter +((encodedChars[i] + shift) - lastCharacter));
            } else {
                encodedChars[i] = (char)(encodedChars[i] + shift);
            }
        }
        return String.valueOf(encodedChars);
    }

    public String decodeText(String text) {
        char[] decodedChars = text.toCharArray();
        for (int i = 0; i < decodedChars.length; i++) {
            if((decodedChars[i] - shift) < firstCharacter){
                decodedChars[i] = (char)(lastCharacter - (firstCharacter -(decodedChars[i] - shift)));
            } else {
                decodedChars[i] = (char)(decodedChars[i] - shift);
            }
        }
        return String.valueOf(decodedChars);
    }


}
