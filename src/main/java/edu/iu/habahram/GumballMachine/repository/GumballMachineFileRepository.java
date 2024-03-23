package edu.iu.habahram.GumballMachine.repository;

import edu.iu.habahram.GumballMachine.model.GumballMachine;
import edu.iu.habahram.GumballMachine.model.GumballMachineRecord;
import edu.iu.habahram.GumballMachine.model.GumballMachineState;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class GumballMachineFileRepository implements IGumballRepository {
    private String DATA_FOLDER_PATH = "data";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String GUMBALL_DATABASE_NAME = "data/gumball-machines.txt";

    public GumballMachineFileRepository() {
        File dataDirectory = new File(DATA_FOLDER_PATH);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    private static void appendToFile(Path path, String content)
            throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        List<GumballMachineRecord> result = new ArrayList<>();
        Path path = Paths.get(GUMBALL_DATABASE_NAME);
        if (Files.exists(path)) {
            List<String> data = Files.readAllLines(path);
            for (String line : data) {
                if (line.trim().length() != 0) {
                    GumballMachineRecord q = GumballMachineRecord.fromLine(line);
                    result.add(q);
                }
            }
        }
        return result;
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        Path path = Paths.get(GUMBALL_DATABASE_NAME);
        if (Files.exists(path)) {
            List<String> data = Files.readAllLines(path);
            for (String line : data) {
                if (line.trim().length() != 0) {
                    if(line.split(",")[0].equals(id)) {
                        GumballMachineRecord r = GumballMachineRecord.fromLine(line);
                        return r;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        String id = gumballMachineRecord.getId();
        if (id != null) {
            update(gumballMachineRecord);
        } else {
            id = add(gumballMachineRecord);
        }
        return id;
    }

    public String add(GumballMachineRecord gumballMachineRecord) throws IOException {
        Path path = Paths.get(GUMBALL_DATABASE_NAME);
        String id = UUID.randomUUID().toString();
        String state = gumballMachineRecord.getState();
        if (state == null) {
            state = GumballMachineState.NO_QUARTER.name();
        }
        GumballMachineRecord record = new GumballMachineRecord(id, state.trim(), gumballMachineRecord.getCount());
        String data = record.toLine(id);
        appendToFile(path, data + NEW_LINE);
        return id;
    }

    public String update(GumballMachineRecord record) throws IOException {
        Path path = Paths.get(GUMBALL_DATABASE_NAME);
        List<GumballMachineRecord> gumballs = findAll();
        String id = record.getId().trim();
        List<String> gumballsAsText = new ArrayList<>();
        for (GumballMachineRecord gumball : gumballs) {
            if (gumball.getId().equals(id)) {
                if(record.getState() != null) {
                    gumball.setState(record.getState());
                }
                if(record.getCount() != null) {
                    gumball.setCount(record.getCount());
                }
            }
            String line = gumball.toLine(gumball.getId());
            gumballsAsText.add(line);
        }
        Files.write(path, gumballsAsText, StandardCharsets.UTF_8);
        return id;
    }
}
