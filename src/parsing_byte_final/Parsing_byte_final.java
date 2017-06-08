/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsing_byte_final;

/**
 *
 * @author Acer
 */
public class Parsing_byte_final {

    /**
     * @param args the command line arguments
     */
    
    public static String parsing(byte[] b)
    {
        
        int index=0;
        index+=43;//takes us to session id length
        System.out.println(b[index+2]);
        int session_id_length= (int) b[index];
        if(session_id_length<0) session_id_length+=256;
        System.out.println(session_id_length+"uuuuu");
        index+=session_id_length+1;
        
        int cipher_suite_length1=(int)b[index];
        //index+=1;//cipher suite length er first byte
        int cipher_suite_length2 =(int)b[index+1];
        if(cipher_suite_length1<0)cipher_suite_length1+=256;
        if(cipher_suite_length2<0)cipher_suite_length2+=256;
        int cipher_suite_length=cipher_suite_length1*256+cipher_suite_length2;
        System.out.println(cipher_suite_length2+"ioio");
        index+=cipher_suite_length+2;//takes us to compression mehtod length
        int compression_length=(int) b[index];
        if(compression_length<0) compression_length+=256;
        System.out.println(compression_length+"ioio");
        index+=compression_length+1;//takes us to extension length
        index+=2;//takes us to extension
       // System.out.println(c);
       if((int)b[index]==0 && (int) b [index+1]==0)
       {
           System.out.println("hola");
       }
       else
       {
           index+=9;
       }
       index+=7;//takes us to server name length
       int server_name_length1=(int)b[index];
       //index+=1;// skip the first byte of server_name_length
       int server_name_length2= (int) b [index+1];
       if(server_name_length1<0)server_name_length1+=256;
       if(server_name_length2<0)server_name_length2+=256;
       int server_name_length=server_name_length1*256+server_name_length2;
       System.out.println(server_name_length);
       index+=2;//takes us to server name
       String url="";
       //String url_final="";
      // byte result
     // StringBuffer sb = new StringBuffer();
       for(int i=index;i<index+server_name_length;i++)
       {
           
           int u=(int)b[i];
            char x=(char)u;
            url+=""+x;
          
       }
     
        return url;
    }
    
     public static byte[] convertHexStringToByteArr(String hexString) {
		byte headerValue[] = null;
		headerValue = new byte[hexString.length() / 2];
		for (int i = 0; i < hexString.length(); i += 2) {
			int ch = hexString.charAt(i);

			// System.out.print( ch );
			if (ch >= 48 && ch <= 57)
				ch -= 48;
			else if (ch >= 65 && ch <= 70)
				ch = (ch - 65) + 10;
			else if (ch >= 97 && ch <= 102)
				ch = (ch - 97) + 10;
			headerValue[i / 2] = (byte) (ch & 15);
			ch = hexString.charAt(i + 1);
			// System.out.print( ch + "  ");
			if (ch >= 48 && ch <= 57)
				ch -= 48;
			else if (ch >= 65 && ch <= 70)
				ch = (ch - 65) + 10;
			else if (ch >= 97 && ch <= 102)
				ch = (ch - 97) + 10;
			headerValue[i / 2] = (byte) (headerValue[i / 2] << 4 | (byte) (ch & 15));
                        //System.out.println(headerValue[i/2]);
                        
		}
		return headerValue;
	}
    
    
    public static void main(String[] args) {
        
       // String str = "16030100c9010000c50303f4251c1d2bed3042bd5134aaf82a042b99512cfb9e1f21c3e82739aff6131f0400001cfafac02bc02fc02cc030cca9cca8c013c014009c009d002f0035000a010000801a1a0000ff010001000000001700150000127777772e69706c6f636174696f6e2e6e65740017000000230000000d00140012040308040401050308050501080606010201000500050100000000001200000010000e000c02683208687474702f312e3175500000000b00020100000a000a00082a2a001d001700180a0a000100";
       String str= "16030100bc010000b803031e6514bb1626dd8e98b63eb5feeb6e4f764fa69d73ba8fc8c2546eb4c40c3e0700001ec02bc02fcca9cca8c02cc030c00ac009c013c01400330039002f0035000a010000710000001500130000107777772e66616365626f6f6b2e636f6d00170000ff01000100000a000a0008001d001700180019000b00020100002300000010000e000c02683208687474702f312e31000500050100000000000d0018001604030503060308040805080604010501060102030201";

        byte []bytes = new byte[str.length()/2];
        bytes = convertHexStringToByteArr(str);
        /*for (int i = 0,index = 0; i < str.length(); i+=2, index ++) {
            bytes[index]= (byte) Integer.parseInt(str.substring(i, i+2),16);
         
             
           System.out.println(bytes[index]);
          
        }*/
        //System.out.println(bytes[4]);
        System.out.println(parsing(bytes));
        
        
       
    }
}
