package redis.entity;

public class Candidate {
  	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public Candidate(String id, String name, String skill){
		this.id = id;
		this.name = name;
		this.skill = skill;
	}
	
	public Candidate() {
		
	}

    private String id;
    private String name;
    private String skill;
}