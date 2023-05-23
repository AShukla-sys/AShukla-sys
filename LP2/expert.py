from typing import List

class Career:
    def __init__(self, name, description, time_required, resources):
        self.name = name
        self.description = description
        self.time_required = time_required
        self.resources = resources

careers = [
    Career("Full Stack Developer", "A full stack developer is a software developer who can work on both the front end and the back end of a web application.", "1-2 years", ["Udacity's Full Stack Web Developer Nanodegree", "The Odin Project", "FreeCodeCamp"]),
    Career("Android Developer", "An Android developer is a software developer who develops applications for the Android operating system.", "6-12 months", ["Udacity's Android Basics Nanodegree", "Udacity's Android Developer Nanodegree", "Google's Android developer documentation"]),
    Career("Front End Developer", "A front end developer is a software developer who works on the user interface of a web application.", "6-12 months", ["FreeCodeCamp", "Udacity's Front End Web Developer Nanodegree", "Codecademy's Front End Developer path"]),
    Career("Back End Developer", "A back end developer is a software developer who works on the server-side of a web application.", "6-12 months", ["Udacity's Back End Web Developer Nanodegree", "Codecademy's Back End Engineer path"]),
    Career("PHP Developer", "A PHP developer is a software developer who develops applications using the PHP programming language.", "6-12 months", ["Udacity's PHP Developer Nanodegree", "Codecademy's PHP path"]),
    Career("Data Scientist", "A data scientist is a professional who collects, analyzes, and interprets data to solve business problems.", "6-12 months", ["Udacity's Data Science Nanodegree", "Coursera's Data Science Specialization", "Johns Hopkins University's Data Science Certificate"])
]

def get_user_info() -> List[str]:
    interests = input("What are your interests? ").split()
    skills = input("What are your skills? ").split()
    interests.extend(skills)  # Include skills as interests as well
    return interests

def recommend_careers(interests: List[str]) -> List[Career]:
    matching_careers = []
    for career in careers:
        if any(interest.lower() in career.description.lower() for interest in interests):
            matching_careers.append(career)
    return matching_careers

def print_recommendations(recommended_careers: List[Career]) -> None:
    if recommended_careers:
        for career in recommended_careers:
            print(f"Career: {career.name}")
            print(f"Description: {career.description}")
            print(f"Time Required: {career.time_required}")
            print("Resources:")
            for resource in career.resources:
                print(f"- {resource}")
            print()
    else:
        print("No matching careers found.")

# Ask the user for their interests and skills
interests = get_user_info()

# Recommend careers based on the user's interests and skills
recommended_careers = recommend_careers(interests)

# Print the recommended careers and resources
print_recommendations(recommended_careers)

