package npm.model;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Process_Details")
public class Process_Details implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "pid")
    private String pId;

    @Column(name = "alias_name")
    private String aliasName;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "cpu")
    private double cpu;

    @Column(name = "memory")
    private String memory;

    @Column(name = "discover_time")
    private Date discoverTime;

    @Column(name = "thread_count")
    private int threadCount;

    @Column(name = "handle_count")
    private int handleCount;

    @Column(name = "peak_working_set")
    private String peakWorkingSet;

    @Column(name = "private_working_set")
    private String privateWorkingSet;

    @Column(name = "paged_pool")
    private String pagedPool;

    @Column(name = "np_pool")
    private String npPool;

    @Column(name = "commit_size")
    private String commitSize;

    @Column(name = "cpu_time")
    private String cpuTime;

    @Column(name = "io_read")
    private long ioRead;

    @Column(name = "io_write")
    private long ioWrite;

    @Column(name = "io_other")
    private long ioOther;

    @Column(name = "io_read_bytes")
    private long ioReadBytes;

    @Column(name = "io_write_bytes")
    private long ioWriteBytes;

    @Column(name = "io_other_bytes")
    private long ioOtherBytes;

    @Column(name = "ip_address")
    private String ipAddress;
    
    @Column(name = "monitoring_param")
    private Boolean monitoringParam;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public double getCpu() {
		return cpu;
	}

	public void setCpu(double cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public Date getDiscoverTime() {
		return discoverTime;
	}

	public void setDiscoverTime(Date discoverTime) {
		this.discoverTime = discoverTime;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public int getHandleCount() {
		return handleCount;
	}

	public void setHandleCount(int handleCount) {
		this.handleCount = handleCount;
	}

	public String getPeakWorkingSet() {
		return peakWorkingSet;
	}

	public void setPeakWorkingSet(String peakWorkingSet) {
		this.peakWorkingSet = peakWorkingSet;
	}

	public String getPrivateWorkingSet() {
		return privateWorkingSet;
	}

	public void setPrivateWorkingSet(String privateWorkingSet) {
		this.privateWorkingSet = privateWorkingSet;
	}

	public String getPagedPool() {
		return pagedPool;
	}

	public void setPagedPool(String pagedPool) {
		this.pagedPool = pagedPool;
	}

	public String getNpPool() {
		return npPool;
	}

	public void setNpPool(String npPool) {
		this.npPool = npPool;
	}

	public String getCommitSize() {
		return commitSize;
	}

	public void setCommitSize(String commitSize) {
		this.commitSize = commitSize;
	}

	public String getCpuTime() {
		return cpuTime;
	}

	public void setCpuTime(String cpuTime) {
		this.cpuTime = cpuTime;
	}

	public long getIoRead() {
		return ioRead;
	}

	public void setIoRead(long ioRead) {
		this.ioRead = ioRead;
	}

	public long getIoWrite() {
		return ioWrite;
	}

	public void setIoWrite(long ioWrite) {
		this.ioWrite = ioWrite;
	}

	public long getIoOther() {
		return ioOther;
	}

	public void setIoOther(long ioOther) {
		this.ioOther = ioOther;
	}

	public long getIoReadBytes() {
		return ioReadBytes;
	}

	public void setIoReadBytes(long ioReadBytes) {
		this.ioReadBytes = ioReadBytes;
	}

	public long getIoWriteBytes() {
		return ioWriteBytes;
	}

	public void setIoWriteBytes(long ioWriteBytes) {
		this.ioWriteBytes = ioWriteBytes;
	}

	public long getIoOtherBytes() {
		return ioOtherBytes;
	}

	public void setIoOtherBytes(long ioOtherBytes) {
		this.ioOtherBytes = ioOtherBytes;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Boolean getMonitoringParam() {
		return monitoringParam;
	}

	public void setMonitoringParam(Boolean monitoringParam) {
		this.monitoringParam = monitoringParam;
	}


    
}
