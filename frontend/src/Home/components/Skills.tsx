import { Icon } from "@iconify/react/dist/iconify.js";

const Skills = () => {
    const skills = [
        {icon: "skill-icons:html", name: 'HTML', progress: 20, years: '2 Years'},
        {icon: "skill-icons:css", name: 'CSS', progress: 20, years: '2 Years'},
        {icon: "skill-icons:javascript", name: 'JavaScript', progress: 10, years: '1 Year'},
        {icon: "skill-icons:java-dark", name: 'Java', progress: 30, years: '3 Years'},
        {icon: "skill-icons:mysql-dark", name: 'MySQL', progress: 20, years: '2 Years'},
        {icon: "skill-icons:python-dark", name: 'Python', progress: 20, years: '2 Years'},
        {icon: "skill-icons:github-dark", name: 'GitHub', progress: 10, years: '1 Year'},
        {icon: "skill-icons:mongodb", name: 'MongoDb', progress: 5, years: '1/2 Year'},
        {icon: "skill-icons:react-dark", name: 'React', progress: 5, years: '1/2 Year'},
        {icon: "skill-icons:nodejs-dark", name: 'Node', progress: 10, years: '1 Year'},
        {icon: "skill-icons:docker", name: 'Docker', progress: 5, years: '1/2 Year'},
        {icon: "skill-icons:spring-dark", name: 'Spring Boot', progress: 5, years: '1/2 Year'},
        {icon: "skill-icons:git", name: 'Git', progress: 5, years: '1/2 Year'},
        {icon: "skill-icons:cpp", name: 'C++', progress: 5, years: '1/2 Year'},
    ];

    return (
      <div className="skills">
        <h3>Skills</h3>
        {skills.map((skill, index) => (
          <div key={index} className="skill">
            <Icon icon={skill.icon} width="35" height="35" />
            <div className='textAndBar'>
                <p>{skill.name}</p>
                <div className="progress">
                  <div className="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow={skill.progress} aria-valuemin={0} aria-valuemax={100} style={{ width: `${skill.progress}%`}}>
                    {skill.years}
                  </div>
                </div>
            </div>
          </div>
        ))}
      </div>
    );
}

export default Skills;