package ru.tanki.persistence;

import ru.tanki.database.DataBase;
import ru.tanki.domain.Gamer;

import java.util.List;
import java.util.Map;

public class GamerPersistance {

    private final DataBase db = DataBase.getInstance();

    private static final String TABLE_NAME = "gamers_time";
    private static final String ID_NAME = "nameId";
    private static final String TIME_NAME = "time";

    public void createGamer(String nameId, int time) {
        String sql = """
        insert into gamers.gamers_time
        (nameId, time)
        values
        ('%s', %d)
        """;
        db.execute(String.format(sql, nameId, time));
    }

    public Gamer getByNameId(String nameId) {
        Map<String, String> fromBd = db.selectByNameId(
                nameId,
                TABLE_NAME,
                TIME_NAME
        );
        if (fromBd == null) {
            return null;
        }
        return convertStudent(fromBd);
    }

    private Gamer convertStudent(Map<String, String> fromBd) {
        return new Gamer(
                fromBd.get(ID_NAME),
                Integer.parseInt(fromBd.get(TIME_NAME))
        );
    }
}
