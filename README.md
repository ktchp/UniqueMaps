# UniqueMaps

**UniqueMaps** is a Minecraft plugin made by **ktchp** that prevents unauthorized copying and locking of signed maps, protecting your map art.

## Features

- ❌ No more map locking
- ✅ Sign your maps with `/signmap`
- 🔓 Unsign your own maps with `/unsignmap`
- 🚫 Prevent non-signers from copying your map
- 🔧 Admin tools for bypass and plugin reload

## Commands

| Command              | Description                                          | Permission             |
|----------------------|------------------------------------------------------|-------------------------|
| `/signmap`           | Sign the currently held map                         | `uniquemaps.sign`       |
| `/unsignmap`         | Unsign your own signed map                          | `uniquemaps.unsign`     |
| `/umaps reload`      | Reload the plugin configuration                     | `uniquemaps.admin`      |
| `/umaps bypass`      | Temporarily bypass signature protection             | `uniquemaps.admin`      |
| `/umaps help`        | Show help menu                                      | `uniquemaps.admin`      |

## Permissions

| Permission            | Description                                           | Default |
|------------------------|-------------------------------------------------------|---------|
| `uniquemaps.sign`      | Allows signing maps                                   | `true`  |
| `uniquemaps.unsign`    | Allows unsigning own maps                             | `true`  |
| `uniquemaps.admin`     | Allows admin commands like reload & bypass            | `op`    |
| `uniquemaps.bypass`    | Internally used for bypass during admin commands      | `op`    |

## Installation

### Option 1: Build it Yourself

1. Clone the repo:
    ```bash
    git clone https://github.com/yourusername/UniqueMaps.git
    cd UniqueMaps
    ```

2. Build with Maven:
    ```bash
    mvn clean package
    ```

3. Copy the `.jar` from the `target/` folder into your server’s `plugins/` directory.

4. Restart your server.

### Option 2: Use the Releases Page

1. Go to the [Releases](https://github.com/ktchp/UniqueMaps/releases).
2. Download the latest `.jar`.
3. Place it into your `plugins/` folder.
4. Restart your server.
