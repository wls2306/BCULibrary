package com.bcu.mapper;
import com.bcu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.bcu.pojo.Seat;

@Mapper
public interface SeatMapper {

    int insert(@Param("pojo") Seat pojo);

    int insertList(@Param("pojos") List<Seat> pojo);

    List<Seat> select(@Param("pojo") Seat pojo);

   Seat findBySeatId(@Param("seatId")int seatId);



    int update(@Param("pojo") Seat pojo);

    int delete(String id);

    int CountAvailableSeat();

    int checkOutBySeatId(@Param("seatId")int seatId);

    String selectSeatUserIdBySeatId(@Param("seatId")Integer seatId);

    User selectSeatUserBySeatId(String seatId);





}
