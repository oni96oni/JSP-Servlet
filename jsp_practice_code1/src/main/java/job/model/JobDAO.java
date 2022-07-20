package job.model;

import java.util.List;

import common.model.AbstractDAO;

public class JobDAO extends AbstractDAO<List<JobDTO>, JobDTO> {
	
	private String mapper = "jobMapper.%s";

	@Override
	public List<JobDTO> selectAll() {
		String mapId = String.format(mapper, "selectAll");
		List<JobDTO> data = session.selectList(mapId);
		return data;
	}

	@Override
	public JobDTO selectId(JobDTO e) {
		String mapId = String.format(mapper, "selectId");
		JobDTO data = session.selectOne(mapId, e.getJobId());
		return data;
	}

	@Override
	public JobDTO selectId(int id) {
		return selectId(String.valueOf(id));
	}
	
	public JobDTO selectId(String id) {
		String mapId = String.format(mapper, "selectId");
		JobDTO data = session.selectOne(mapId, id);
		return data;
	}

	@Override
	public int rowCount() {
		String mapId = String.format(mapper, "rowCount");
		int count = session.selectOne(mapId);
		return count;
	}

	@Override
	public boolean insertData(JobDTO e) {
		String mapId = String.format(mapper, "insertData");
		int result = session.insert(mapId, e);
		return result == 1 ? true : false;
	}

	@Override
	public boolean updateData(JobDTO e) {
		String mapId = String.format(mapper, "updateData");
		int result = session.update(mapId, e);
		return result == 1 ? true : false;
	}

	@Override
	public boolean deleteData(int id) {
		return deleteData(String.valueOf(id));
	}
	
	public boolean deleteData(String id) {
		String mapId = String.format(mapper, "deleteData", id);
		int result = session.delete(mapId, id);
		return result == 1 ? true : false;
	}

	@Override
	public boolean deleteData(JobDTO e) {
		String mapId = String.format(mapper, "deleteData");
		int result = session.delete(mapId, e);
		return result == 1 ? true : false;
	}
	
}
