package com.fastcampus.projectboardadmin.domain.converter;

import com.fastcampus.projectboardadmin.domain.constant.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**[도메인 설계] - RoleTypesConverter*/
//Set<RoleType>을 String 으로 convert
@Converter
public class RoleTypesConverter implements AttributeConverter<Set<RoleType>, String> {

    //구분자
    private static final String DELIMITER = ",";

    //엔티티를 DB에 저장할 때 , Set<RoleType> -> String
    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
        //attribute 에서 뽑아낸 RoleType 의 이름(String)들을 정렬 후, 콤마를 이용해 하나의 문자열로 조인한다.
        return attribute.stream().map(RoleType::name).sorted().collect(Collectors.joining(DELIMITER));
    }

    //DB에 있던 값을 엔티티로 , String -> Set<RoleType>
    @Override
    public Set<RoleType> convertToEntityAttribute(String dbData) {
        //dbData 을 콤마 기준으로 쪼개고, RoleType 으로 변환해 Set 으로 전환
        return Arrays.stream(dbData.split(DELIMITER)).map(RoleType::valueOf).collect(Collectors.toSet());
    }

}