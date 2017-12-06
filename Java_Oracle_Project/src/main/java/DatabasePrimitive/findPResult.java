package DatabasePrimitive;

/**
 * Created by Rey on 23.04.2017.
 */
public class findPResult {
    String EMPNO;
    String	ENAME;
    String	JOB;
    String	MGR;
    String	HIREDATE;
    String	SAL;
    String COMM;
    String DEPTNO;
    String	DNAME;
    String	LOC;

    public findPResult(String EMPNO, String	ENAME, String	JOB,  String	MGR, String	HIREDATE, String	SAL,	String COMM,
                       String DEPTNO, String	DNAME, String	LOC	){
       this.COMM = COMM;
       this.DEPTNO = DEPTNO;
       this.DNAME =DNAME;
       this.EMPNO = EMPNO;
       this.ENAME = ENAME;
       this.HIREDATE = HIREDATE;
       this.JOB = JOB;
       this.LOC= LOC;
       this.MGR = MGR;
       this.SAL =SAL;
    }

    @Override
    public String toString() {
        return "\n"+EMPNO+" "+ENAME +" "+JOB+" "+MGR+" "+HIREDATE+" "+SAL+" "+COMM+" "+DEPTNO+" "+DNAME+" "+LOC;
    }
}
