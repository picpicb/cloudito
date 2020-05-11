package esipe.fr.cloudito_encryption.persistence;

import esipe.fr.cloudito_encryption.model.Coordinate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CoordinateRowMapper implements RowMapper<Coordinate> {

    @Override
    public Coordinate mapRow(ResultSet rs, int rowNum) throws SQLException {

        Coordinate coordinate = new Coordinate();
        coordinate.setId(rs.getLong("id"));
        coordinate.setName(rs.getString("name"));
        coordinate.setY(rs.getInt("y"));
        coordinate.setX(rs.getInt("x"));

        return coordinate;

    }
}