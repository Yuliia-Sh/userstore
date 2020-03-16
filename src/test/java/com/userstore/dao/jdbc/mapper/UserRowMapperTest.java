package com.userstore.dao.jdbc.mapper;



import org.junit.Test;

import java.sql.ResultSet;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserRowMapperTest {
    @Test
    public void mapRow() throws Exception {
        UserRowMapper userRowMapper=new UserRowMapper();

        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getInt("salary")).thenReturn(300);
        when(mockResultSet.getString("first_name")).thenReturn("Yulia");
        when(mockResultSet.getString("last_name")).thenReturn("Shin");

        com.userstore.entity.User actualUser = userRowMapper.mapRow(mockResultSet);

        assertNotNull(actualUser);
        assertEquals(1, actualUser.getId());
        assertEquals(300, actualUser.getSalary());
        assertEquals("Yulia", actualUser.getFirstName());
        assertEquals("Shin", actualUser.getLastName());
    }

}