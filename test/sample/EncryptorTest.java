package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EncryptorTest {

    Encryptor en = new Encryptor();

    @Test
    public void ShouldReturnEncodedText(){
        //given
        String message = "All my friends are the best";
        int shift = 3;
        //when
        en.setShift(shift);
        String encodedText = en.encodeText(message);
        //than
        Assertions.assertEquals("Doo#p|#iulhqgv#duh#wkh#ehvw", encodedText);
    }

    @Test
    public void ShouldReturnEncodedText1(){
        //given
        String message = "Aaaa";
        int shift = 3;
        //when
        en.setShift(shift);
        String encodedText = en.encodeText(message);
        //than
        Assertions.assertEquals("Dddd", encodedText);
    }

    @Test
    public void ShouldReturnDecodedText1(){
        //given
        String message = "Dod#pd#nrwd";
        int shift = 3;
        //when
        en.setShift(shift);
        String decodedText = en.decodeText(message);
        //than
        Assertions.assertEquals("Ala ma kota", decodedText);
    }

    @Test
    public void ShouldReturnDecodedText(){
        //given
        String message = "Doo#p|#iulhqgv#duh#wkh#ehvw";
        int shift = 3;
        //when
        en.setShift(shift);
        String decodedText = en.decodeText(message);
        //than
        Assertions.assertEquals("All my friends are the best", decodedText);
    }

    @Test
    public void ShouldReturnDecodedText2(){
        //given
        String message = "Dddd";
        int shift = 3;
        //when
        en.setShift(shift);
        String decodedText = en.decodeText(message);
        //than
        Assertions.assertEquals("Aaaa", decodedText);
    }

}
