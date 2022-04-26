package com.ridango.retro.retro.mapper;

import com.ridango.retro.retro.dto.RetroBoardListResponse;
import com.ridango.retro.retro.dto.RetroBoardRequest;
import com.ridango.retro.retro.dto.RetroBoardResponse;
import com.ridango.retro.retro.entity.BoardColumnModel;
import com.ridango.retro.retro.entity.BoardModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RetroBoardMapper {
    RetroBoardMapper INSTANCE = Mappers.getMapper(RetroBoardMapper.class);

    RetroBoardResponse toResponse(BoardModel model);

    default BoardModel toModel(RetroBoardRequest request) {
        BoardModel model = new BoardModel();
        model.setColumns(new ArrayList<>());
        model.setName(request.getName());
        model.setTeamName(request.getTeamName());
        model.setRetroDate(Date.valueOf(LocalDate.now()));
        model.setExpirationDate(Date.valueOf(LocalDate.now().plusYears(3)));
        for (String s : request.getColumns()) {
            BoardColumnModel bcm = new BoardColumnModel();
            bcm.setName(s);
            bcm.setBoardModel(model);
            model.getColumns().add(bcm);
        }
        return model;
    }

    List<RetroBoardListResponse> toListResponse(List<BoardModel> model);
}
