package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long,TimeEntry> dataBase = new HashMap<>();
    private Long nextKey = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        dataBase.put(nextKey, new TimeEntry(nextKey,
                timeEntryToCreate.getProjectId(),
                timeEntryToCreate.getUserId(),
                timeEntryToCreate.getDate(),
                timeEntryToCreate.getHours()));
        TimeEntry response = dataBase.get(nextKey);
        nextKey++;
        return response;
    }

    @Override
    public TimeEntry find(long id) {
        return dataBase.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(dataBase.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        dataBase.put(id, timeEntry);
        return dataBase.get(id);
    }

    @Override
    public TimeEntry delete(long id) {
        TimeEntry removed = dataBase.get(id);
        dataBase.remove(id);
        return removed;
    }
}

