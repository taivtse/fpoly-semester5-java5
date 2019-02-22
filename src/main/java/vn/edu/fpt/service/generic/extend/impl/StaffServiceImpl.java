package vn.edu.fpt.service.generic.extend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.command.StaffCommand;
import vn.edu.fpt.common.paging.PageRequest;
import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.dao.generic.extend.StaffDao;
import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.dto.StaffLiveSearchDto;
import vn.edu.fpt.entity.StaffEntity;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.mapper.StaffMapper;
import vn.edu.fpt.service.generic.extend.StaffService;
import vn.edu.fpt.service.generic.impl.ActiveEntityServiceImpl;
import vn.edu.fpt.util.FileUploadUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl extends ActiveEntityServiceImpl<Integer, StaffDto> implements StaffService {

    private StaffMapper mapper;
    private StaffDao staffDao;

    @Autowired
    public StaffServiceImpl(@Qualifier("staffDaoImpl") ActiveEntityDao activeEntityDao,
                            @Qualifier("staffMapper") AbstractMapper abstractMapper) {
        super(activeEntityDao, abstractMapper);
        this.mapper = (StaffMapper) abstractMapper;
        this.staffDao = (StaffDao) activeEntityDao;
    }

    @Override
    public StaffDto findOneActiveByCode(String code) {
        StaffEntity entity = staffDao.findOneActiveByCode(code);
        if (entity != null)
            return mapper.entityToDto(entity);
        return null;
    }

    @Override
    public List<StaffDto> findAllActiveByName(String name) {
        List<StaffDto> dtoList = new ArrayList<>();
        staffDao.findAllActiveByName(name).forEach(staffEntity -> dtoList.add(mapper.entityToDto(staffEntity)));
        return dtoList;
    }

    @Override
    public List<StaffLiveSearchDto> findAllActiveByCodeInLiveSearch(String staffCode) {
        List<StaffLiveSearchDto> dtoList = new ArrayList<>();
        Pageable pageable = new PageRequest(1, 5, null);
        staffDao.findAllActiveByCode(pageable, staffCode).forEach(staffEntity -> {
            StaffLiveSearchDto staffLiveSearchDto = new StaffLiveSearchDto();
            staffLiveSearchDto.setId(staffEntity.getId());
            staffLiveSearchDto.setCode(staffEntity.getCode());
            staffLiveSearchDto.setName(staffEntity.getName());

            dtoList.add(staffLiveSearchDto);
        });
        return dtoList;
    }

    @Override
    public StaffLiveSearchDto findOneActiveByCodeInLiveSearch(String staffCode) {
        StaffEntity entity = staffDao.findOneActiveByCode(staffCode);
        StaffLiveSearchDto staffLiveSearchDto = null;
        if (entity != null) {
            staffLiveSearchDto = new StaffLiveSearchDto();
            staffLiveSearchDto.setId(entity.getId());
            staffLiveSearchDto.setCode(entity.getCode());
            staffLiveSearchDto.setName(entity.getName());
            staffLiveSearchDto.setDepartName(entity.getDepartEntity().getName());
        }
        return staffLiveSearchDto;
    }

    @Override
    public StaffDto saveWithActiveStatus(StaffCommand command) throws Exception {
        StaffDto dto = command.getPojo();

        DepartDto departDto = new DepartDto();
        departDto.setId(command.getDepartId().trim());
        dto.setDepartDto(departDto);

        dto = super.saveWithActiveStatus(dto);

        if (command.getStaffPhoto().getSize() > 0) {
//        upload file
            String uploadedFileName = FileUploadUtil.getInstance().setUploadFileIsImage()
                    .write(command.getStaffPhoto(), SystemConstant.STAFF_UPLOAD_PATH, dto.getCode());

            dto.setPhoto(uploadedFileName);
        }

        return super.updateWithActiveStatus(dto);
    }

    @Override
    public StaffDto updateWithActiveStatus(StaffCommand command) throws Exception {
        StaffDto dto = command.getPojo();

        DepartDto departDto = new DepartDto();
        departDto.setId(command.getDepartId().trim());
        dto.setDepartDto(departDto);

        if (command.getStaffPhoto().getSize() > 0) {
//        upload file
            String uploadedFileName = FileUploadUtil.getInstance().setUploadFileIsImage()
                    .write(command.getStaffPhoto(), SystemConstant.STAFF_UPLOAD_PATH, dto.getCode());

            dto.setPhoto(uploadedFileName);
        } else {
//            set old photo before update
            StaffDto oldDto = this.findOneById(dto.getId());
            dto.setPhoto(oldDto.getPhoto());
        }

        return super.updateWithActiveStatus(dto);
    }
}
