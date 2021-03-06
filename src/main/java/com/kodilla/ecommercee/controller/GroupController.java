package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupNotFoundException;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.DbGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @Autowired
    private DbGroupService dbGroupService;
    @Autowired
    private GroupMapper groupMapper;

    @GetMapping(value = "getGroups")
    public List<GroupDto> getGroups() {
        return groupMapper.mapTogGroupDtoList(dbGroupService.getAllGroups());
    }

    @GetMapping(value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(dbGroupService.getGroup(groupId).orElseThrow(GroupNotFoundException::new));
    }

    @DeleteMapping(value = "deleteGroup")
    public void deleteGroup(@RequestParam Long groupId) {
        dbGroupService.deleteGroup(groupId);
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(dbGroupService.updateGroup(groupDto.getId(), groupDto.getGroupName()));
    }

    @PostMapping(value = "createGroup")
    public void addGroup(@RequestBody GroupDto groupDto) {
        dbGroupService.createGroup(groupMapper.mapToGroup(groupDto));
    }
}
