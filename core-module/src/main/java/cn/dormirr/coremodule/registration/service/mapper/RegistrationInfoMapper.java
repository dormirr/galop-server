package cn.dormirr.coremodule.registration.service.mapper;

import cn.dormirr.commonmodule.base.BaseMapper;
import cn.dormirr.coremodule.registration.domain.RegistrationInfoEntity;
import cn.dormirr.coremodule.registration.service.dto.RegistrationInfoDto;
import org.mapstruct.Mapper;

/**
 * @author ZhangTianCi
 */
@Mapper(componentModel = "spring")
public interface RegistrationInfoMapper extends BaseMapper<RegistrationInfoDto, RegistrationInfoEntity> {

    /**
     * RegistrationInfoDto 转 RegistrationInfoEntity
     *
     * @param registrationInfoDto RegistrationInfoDto
     * @return RegistrationInfoEntity
     */
    @Override
    RegistrationInfoEntity toEntity(RegistrationInfoDto registrationInfoDto);

    /**
     * RegistrationInfoEntity 转 RegistrationInfoDto
     *
     * @param registrationInfoEntity RegistrationInfoEntity
     * @return RegistrationInfoDto
     */
    @Override
    RegistrationInfoDto toDto(RegistrationInfoEntity registrationInfoEntity);
}
