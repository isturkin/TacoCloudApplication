package com.ivanturkin.cloud.app.taco.repository;

import com.ivanturkin.cloud.app.taco.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);

        for (String ingredient : taco.getIngredients()) {
            saveIngredientsToTaco(ingredient, tacoId);
        }

        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedDate(new Date());

        PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(
                "INSERT INTO tacos (name, createdDate) VALUES (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        statementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psCreator = statementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedDate().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psCreator, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientsToTaco(String ingredient, long tacoId) {
        jdbc.update("INSERT INTO tacos_ingredients (taco, ingredient) VALUES (?, ?)",
                tacoId, ingredient);
    }
}
