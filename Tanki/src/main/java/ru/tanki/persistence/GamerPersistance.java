package ru.tanki.persistence;

import ru.tanki.database.DataBase;
import ru.tanki.domain.Gamer;

import java.util.List;
import java.util.Map;

public class GamerPersistance {

    private final DataBase db = DataBase.getInstance();

    private static final String TABLE_NAME = "gamers";
    private static final String ID_NAME = "nameId";
    private static final String TIME_NAME = "gametime";

    public void createGamer(String nameId, int time) {
        String sql = """
        insert into cursach.gamers
        (nameId, gametime)
        values
        ('%s', %d)
        """;
        db.execute(String.format(sql, nameId, time));
    }

    public void deleteAllGamers() {
        String sql = """
                delete from cursach.gamers
                """;
        db.execute(String.format(sql));
    }

    public void updateGamer(String nameId, int time) {
        String sql = """
                update cursach.gamers
                    set gametime = %d
                where nameId = '%s'
                """;
        db.execute(String.format(sql, time, nameId));
    }

    public void updateTable() {
        String sql = """
                update cursach.gamers
                set gametime = gametime
                """;

        db.execute(String.format(sql));
    }

    //закоментил, пока не надо
//    public Gamer getByNameId(String nameId) {
//        Map<String, String> fromBd = db.selectByNameId(
//                nameId,
//                TABLE_NAME,
//                ID_NAME,
//                TIME_NAME
//        );
//        if (fromBd == null) {
//            return null;
//        }
//        return convertGamer(fromBd);
//    }

    public List<Gamer> getAll() {
        List<Map<String, String>> fromDB = db.selectAll(
                TABLE_NAME,
                ID_NAME,
                TIME_NAME
        );

        return fromDB.stream()
                .map(this::convertGamer)
                .toList();
    }

    private Gamer convertGamer(Map<String, String> fromBd) {
        return new Gamer(
                fromBd.get(ID_NAME),
                Integer.parseInt(fromBd.get(TIME_NAME))
        );
    }
}
