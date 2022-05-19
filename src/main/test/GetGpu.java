import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetGpu {

    @Test
    public void gpu() throws IOException {

        try {
            String line;
            Process p = Runtime.getRuntime().exec("nvidia-smi.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line); //<-- Parse data here.
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }

    }
}


