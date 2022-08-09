import os


def write_to_properties(propertiesFileSrc: str):
    f = open(propertiesFileSrc, "a")
    f.write("#Creadetials")
    f.write(f"auth0.audience={os.getenv('AUTH0_AUDIENCE')}")
    f.write(f"auth0.domain={os.getenv('AUTH0_DOMAIN')}")
    f.write(f"")
    f.write(f"linkedin.consumerKey={os.getenv('LINKED_IN_CONSUMER_KEY')}")
    f.write(f"linkedin.consumerSecret={os.getenv('LINKED_IN_CONSUMER_SECRET')}")
    f.write(f"")
    f.write(f"spring.data.mongodb.database={os.getenv('MONGO_DB_NAME')}")
    f.write(f"spring.data.mongodb.password={os.getenv('MONGO_DB_PASS')}")
    f.write(f"spring.data.mongodb.username={os.getenv('MONGO_DB_USERNAME')}")
    f.close()

def read_from_properties(propertiesFileSrc: str):
    f = open(propertiesFileSrc, "r")
    print(f.read())
    f.close()

if __name__ == "__main__":
    propertiesFileSrc = "src/main/resources/application.properties"
    write_to_properties(propertiesFileSrc)
    read_from_properties(propertiesFileSrc)

