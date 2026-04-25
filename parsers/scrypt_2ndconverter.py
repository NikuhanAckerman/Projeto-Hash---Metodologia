def parse_spring_scrypt(hash_str):
    if not hash_str.startswith("$"):
        raise ValueError("Formato inválido")

    parts = hash_str.strip().split("$")

    # ['', params, salt, hash]
    if len(parts) != 4:
        raise ValueError("Formato inesperado")

    params_hex = parts[1]
    salt_b64 = parts[2]
    hash_b64 = parts[3]

    params = int(params_hex, 16)

    log2_n = (params >> 16) & 0xFFFF
    r = (params >> 8) & 0xFF
    p = params & 0xFF

    N = 2 ** log2_n

    return f"SCRYPT:{N}:{r}:{p}:{salt_b64}:{hash_b64}"


def convert_file():
    input_file = "scryptHashes_NORDPASS.txt"
    output_file = "scryptHashesCONVERTED2_NORDPASS.txt"

    total = 0
    success = 0

    with open(input_file, "r", encoding="utf-8") as infile, \
         open(output_file, "w", encoding="utf-8") as outfile:

        for line in infile:
            total += 1
            line = line.strip()

            if not line:
                continue

            try:
                # já está no formato hashcat → só copia
                if line.startswith("SCRYPT:"):
                    outfile.write(line + "\n")
                    success += 1
                    continue

                converted = parse_spring_scrypt(line)
                outfile.write(converted + "\n")
                success += 1

    print("\n=== RESUMO ===")
    print(f"Total: {total}")
    print(f"Convertidos: {success}")

# ===== EXECUÇÃO =====
if __name__ == "__main__":
    convert_file()
