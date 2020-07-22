package jaspergenerator.demo.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class Reader {

    private static final Logger log = LoggerFactory.getLogger(Reader.class);

    public static InputStream smartLoad(String templatePath) throws IOException {
        boolean isInternalResource = false;
        if (!templatePath.equals("") && templatePath.substring(0, 1).equals("/")) isInternalResource = true;
        return loadFile(templatePath, isInternalResource);
    }

    public static InputStream loadFile(String templatePath) throws IOException {
        return loadFile(templatePath, false);
    }

    public static InputStream loadFile(String templatePath, boolean isInternalResource) throws IOException {
        if (isInternalResource) {
            Resource resource = new ClassPathResource(templatePath);
            InputStream is;
            try {
                is = resource.getInputStream();
            } catch (IOException e) {
                MDC.put("read-file", templatePath);
                MDC.put("error", e.getMessage());
                log.warn("failed to read file ");
                MDC.remove("read-file");
                MDC.remove("error");
                throw e;
            }
            return is;
        }

        InputStream is;
        try {
            File file = new File(templatePath);
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            MDC.put("read-file", templatePath);
            MDC.put("error", e.getMessage());
            log.warn("failed to read file ");
            MDC.remove("read-file");
            MDC.remove("error");
            throw new MissingFileException(String.format("file not found: %s", e.getMessage()));
        }

        return is;
    }

}
