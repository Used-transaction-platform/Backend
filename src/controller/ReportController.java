package me.kalriz.checkmate.app.controller;

import java.util.List;
import me.kalriz.checkmate.app.dao.BoardDAO;
import me.kalriz.checkmate.app.model.BoardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping(path = {"/api/report/"})
public class ReportController
{
  private static final Logger Log = LoggerFactory.getLogger(me.kalriz.checkmate.app.CheckMateApplication.class);
  
  @Autowired
  private BoardDAO boardDao;

  
  @ResponseBody
  @GetMapping(path = {"count"})
  public int reportTotalCount() { return this.boardDao.getReportTotalCount(); }


  
  @ResponseBody
  @GetMapping(path = {"list"})
  public List<BoardModel> reportList() { return this.boardDao.getReportBoard(); }


  
  @ResponseBody
  @GetMapping(path = {"search"})
  public List<BoardModel> reportListByID(String email) { return this.boardDao.getReportBoardByID(email); }
}
