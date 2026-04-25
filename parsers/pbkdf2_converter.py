import base64

INPUT_FILE = "C:\hashcat-7.1.2\experimento_benchmark_seguranca\pbkdf2_10kList.txt"
OUTPUT_FILE = "C:\hashcat-7.1.2\experimento_benchmark_seguranca\pbkdf2_10kList_CONVERTED.txt"
ITERATIONS = 310000  # Spring Security v5.8 default

def spring_to_hashcat(line, iterations=ITERATIONS):
    line = line.strip()
    if not line:
        return None
    
    # First 16 bytes (32 hex chars) = salt
    salt_hex = line[:32]
    # Remaining 32 bytes (64 hex chars) = hash
    hash_hex = line[32:]
    
    # Convert hex → base64
    salt_b64 = base64.b64encode(bytes.fromhex(salt_hex)).decode()
    hash_b64 = base64.b64encode(bytes.fromhex(hash_hex)).decode()
    
    return f"sha256:{iterations}:{salt_b64}:{hash_b64}"

def main():
    with open(INPUT_FILE, "r") as infile, open(OUTPUT_FILE, "w") as outfile:
        for line in infile:
            converted = spring_to_hashcat(line)
            if converted:
                outfile.write(converted + "\n")

if __name__ == "__main__":
    main()
