import os


def write_to_properties(propertiesFileSrc: str):
    f = open(propertiesFileSrc, "a")
    f.write("#Creadetials\n")
    f.write(f"auth0.audience={os.getenv('AUTH0_AUDIENCE')}\n")
    f.write(f"auth0.clientSecret={os.getenv('AUTH0_CLIENT_SECRET')}\n")
    f.write(f"auth0.domain={os.getenv('AUTH0_DOMAIN')}\n")
    f.write(f"\n")
    f.write(f"spring.data.mongodb.database={os.getenv('MONGO_DB_NAME')}\n")
    f.write(f"spring.data.mongodb.password={os.getenv('MONGO_DB_PASS')}\n")
    f.write(f"spring.data.mongodb.username={os.getenv('MONGO_DB_USERNAME')}\n")
    f.close()

def read_from_properties(propertiesFileSrc: str):
    f = open(propertiesFileSrc, "r")
    print(f.read())
    f.close()

if __name__ == "__main__":
    propertiesFileSrc = "src/main/resources/application.properties"
    write_to_properties(propertiesFileSrc)
    read_from_properties(propertiesFileSrc)

