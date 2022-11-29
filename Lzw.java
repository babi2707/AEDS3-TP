import java.util.*;

public class Lzw {
     /** Compress a string to a list of output symbols. */
     public static List<Integer> compressao(String text) {
        // Dicionario 
        int dicioTam = 256;
        Map<String,Integer> dicionario = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dicionario.put("" + (char)i, i);
        
        String w = "";
        List<Integer> resp = new ArrayList<Integer>();
        for (char c : text.toCharArray()) {
            String wc = w + c;
            if (dicionario.containsKey(wc))
                w = wc;
            else {
                resp.add(dicionario.get(w));
                // Add wc to the dicionario.
                dicionario.put(wc, dicioTam++);
                w = "" + c;
            }
        }
 
        // Output the code for w.
        if (!w.equals(""))
            resp.add(dicionario.get(w));
        return resp;
    }
    
    /** Decompress a list of output ks to a string. */
    public static String descompressao(List<Integer> text) {
        // Build the dictionary.
        int dicioTam = 256;
        Map<Integer,String> dicionario = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dicionario.put(i, "" + (char)i);
        
        String w = "" + (char)(int)text.remove(0);
        StringBuffer resp = new StringBuffer(w);
        for (int k : text) {
            String entry;
            if (dicionario.containsKey(k))
                entry = dicionario.get(k);
            else if (k == dicioTam)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
            
            resp.append(entry);
            
            // Add w+entry[0] to the dicionario.
            dicionario.put(dicioTam++, w + entry.charAt(0));
            
            w = entry;
        }
        return resp.toString();
    }

}
