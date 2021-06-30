package com.example.schoolmanagerrestresourceserver.models.mappers;

import com.example.schoolmanagerrestresourceserver.models.dtos.ConnectionDTO;
import com.example.schoolmanagerrestresourceserver.models.entities.Connection;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ConnectionMapper {

    public static ConnectionDTO fromConnection(Connection connection) {
        if (connection == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        ConnectionDTO dto = new ConnectionDTO();
        dto.setCourseId(connection.getCourseId());
        dto.setUserId(connection.getUserId());
        return dto;
    }

    public static List<ConnectionDTO> fromConnectionList(List<Connection> connections) {
        return connections.stream().map(ConnectionMapper::fromConnection).collect(Collectors.toList());
    }

}
