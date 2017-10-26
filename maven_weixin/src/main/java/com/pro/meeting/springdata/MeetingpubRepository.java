package com.pro.meeting.springdata;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pro.meeting.bean.MeetingPub;

public interface MeetingpubRepository extends JpaRepository<MeetingPub, String> {
	// 生成会议编号
	@Query(value = "select max(pcode) from meetingpub where left (pcode,8)=?1", nativeQuery = true)
	public String findMaxPcode(String code);

	// 发布的会议
	public List<MeetingPub> findByUidOrderByPcodeDesc(String uid);

	// 发单排行榜
	@Query(value = "SELECT pub.uid as uid ,COUNT(*) as count,w.nickname as nickname,w.headimgurl as headimgurl FROM meetingpub pub,users u,weiuser w where pub.uid=u.uid AND u.wid= w.wid GROUP BY pub.uid", nativeQuery = true)
	public List<Object[]> findMeetingpubRank();

	
	//抢单 列出发单的列表数据
	@Query(value="select DISTINCT  pub.*  from meetingpub pub LEFT JOIN meetinggrap grap  on pub.pid=grap.pid where (grap.gstatus=0  or grap.gstatus is null)  AND pub.pid NOT IN(SELECT g.pid FROM meetinggrap g WHERE g.uid=?1)  ",nativeQuery=true)
	public List<MeetingPub> findAllByGrap(String uid);
	
	//根据类型查询列表数据
	@Query(value="select DISTINCT  pub.*  from meetingpub pub LEFT JOIN meetinggrap grap  on pub.pid=grap.pid where ( grap.gstatus=0  or grap.gstatus is null ) and pub.ptype=?1  AND pub.pid NOT IN(SELECT g.pid FROM meetinggrap g WHERE g.uid=?2)",nativeQuery=true)
	public List<MeetingPub>findAllByGrapByPtype(String ptype,String uid);
	
	//我的抢单列表
	@Query(value=" select pub.* from meetingpub pub LEFT JOIN meetinggrap grap on pub.pid=grap.pid"
			+ " where grap.uid=?1   order by pcode DESC ",nativeQuery=true)
	public  List<MeetingPub> findMeetingpubGrap(String uid);
	
	//选择讲者  去匹配抢单
	@Query(value="select u.uid, u.uname,u.province, grap.gremark,grap.pid,grap.gstatus  from meetinggrap grap LEFT JOIN "
			+ "users u on grap.uid=u.uid where pid=?1",nativeQuery=true)
	public List<Object[]> findGrapBypid(String pid);
	
}
