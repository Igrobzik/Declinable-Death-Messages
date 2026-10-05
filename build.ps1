$ErrorActionPreference = "Stop"

trap {
    Write-Host ""
    Write-Host "============================================================" -ForegroundColor Red
    Write-Host "UNHANDLED ERROR" -ForegroundColor Red
    Write-Host "============================================================" -ForegroundColor Red
    Write-Host ""
    Write-Host $_ -ForegroundColor Red
    Write-Host ""
    Read-Host "Press Enter to close"
    exit 1
}

# ============================================================
# Translatable Fools - build script
# ============================================================

$Root = Split-Path -Parent $MyInvocation.MyCommand.Path
$BuildDir = Join-Path $Root "build"
$DepsDir = Join-Path $Root ".build-deps"

# ============================================================
# Translation directory
# ============================================================

$LangDir = Join-Path $Root "2.0\lang"
$LanguagesFile = Join-Path $LangDir "languages.txt"

Add-Type -AssemblyName System.IO.Compression
Add-Type -AssemblyName System.IO.Compression.FileSystem

$JavaJar = "https://libraries.minecraft.net/org/lwjgl/lwjgl/lwjgl/2.9.4-nightly-20150209/lwjgl-2.9.4-nightly-20150209.jar"
$JavaUtilJar = "https://libraries.minecraft.net/org/lwjgl/lwjgl/lwjgl_util/2.9.4-nightly-20150209/lwjgl_util-2.9.4-nightly-20150209.jar"

$LwjglJar = Join-Path $DepsDir "lwjgl-2.9.4-nightly-20150209.jar"
$LwjglUtilJar = Join-Path $DepsDir "lwjgl_util-2.9.4-nightly-20150209.jar"

# ============================================================
# Version configuration
# ============================================================

$Versions = @(

    @{
        DisplayName = "2.0 Blue"
        Id          = "2.0-blue"
        JarName     = "2.0-blue.jar"

        Sources = @(
            "2.0-blue-purple\bko.java"
            "2.0-blue-purple\bia.java"
        )

        Classes = @(
            "bko.class"
            "bia.class"
        )

        # Optional 2.0 font centering fix
        FontFixSource = "2.0-blue-purple\awz.java"
        FontFixClass  = "awz.class"

        Sha256 = "1A8ECA28EFA9B58F5546FD66A5B3BBBBF61F7F1ECB0730B29706217D8AAF0BD9"

        Url = "https://vault.omniarchive.uk/archive/java/client-april-fools/2.0-blue.jar"
    }

    @{
        DisplayName = "2.0 Purple"
        Id          = "2.0-purple"
        JarName     = "2.0-purple.jar"

        Sources = @(
            "2.0-blue-purple\bko.java"
            "2.0-blue-purple\bia.java"
        )

        Classes = @(
            "bko.class"
            "bia.class"
        )

        # Optional 2.0 font centering fix
        FontFixSource = "2.0-blue-purple\awz.java"
        FontFixClass  = "awz.class"

        Sha256 = "1184BA5E91D2742B89108428778F8590A61704339CF07B4ED84C84CF67D674E3"

        Url = "https://vault.omniarchive.uk/archive/java/client-april-fools/2.0-purple.jar"
    }

    @{
        DisplayName = "2.0 Red"
        Id          = "2.0-red"
        JarName     = "2.0-red.jar"

        Sources = @(
            "2.0-red\bkg.java"
        )

        Classes = @(
            "bkg.class"
        )

        # Optional 2.0 font centering fix
        FontFixSource = "2.0-red\awv.java"
        FontFixClass  = "awv.class"

        Sha256 = "333A54B6250138D837E17CB049633EFC0D55DE6EDFCCF131D55DEF3F0FCB10D3"

        Url = "https://vault.omniarchive.uk/archive/java/client-april-fools/2.0-red.jar"
    }

    @{
        DisplayName = "2.0 Preview"
        Id          = "2.0-preview"
        JarName     = "2.0-preview.jar"

        Sources = @(
            "2.0-preview\bma.java"
        )

        Classes = @(
            "bma.class"
        )

        # Optional 2.0 font centering fix
        FontFixSource = "2.0-preview\ayb.java"
        FontFixClass  = "ayb.class"

        Sha256 = "555D0F7A887964CA34676825AB76D0CBA69E52FDA9BACEA0FEBA7921EABD22CB"

        Url = "https://vault.omniarchive.uk/archive/java/client-april-fools/2.0-preview.jar"
    }

)

# ============================================================
# Version selection
# ============================================================

Write-Host ""
Write-Host "========================================"
Write-Host " Minecraft April Fools Builder"
Write-Host "========================================"
Write-Host ""

for ($i = 0; $i -lt $Versions.Count; $i++) {
    Write-Host "[$($i + 1)] $($Versions[$i].DisplayName)"
}

$AllOption = $Versions.Count + 1

Write-Host "[$AllOption] All versions"
Write-Host ""

$Selection = Read-Host "Select version(s)"

if ($Selection.Trim() -eq $AllOption.ToString()) {

    $SelectedVersions = $Versions

}
else {

    $Numbers = @(
        $Selection -split "," |
        ForEach-Object { $_.Trim() } |
        Where-Object { $_ -ne "" }
    )

    if ($Numbers.Count -eq 0) {
        throw "No version selected."
    }

    $SelectedVersions = @()

    foreach ($Number in $Numbers) {

        $Index = 0

        if (-not [int]::TryParse($Number, [ref]$Index)) {
            throw "Invalid version number: $Number"
        }

        if ($Index -lt 1 -or $Index -gt $Versions.Count) {
            throw "Invalid version number: $Number"
        }

        $SelectedVersions += $Versions[$Index - 1]
    }

    $SelectedVersions = @(
        $SelectedVersions |
        Group-Object Id |
        ForEach-Object { $_.Group[0] }
    )
}

Write-Host ""
Write-Host "Selected versions:"

foreach ($Version in $SelectedVersions) {
    Write-Host "  - $($Version.DisplayName)"
}

# ============================================================
# 2.0 font centering fix
# ============================================================

$Has2Point0 = @(
    $SelectedVersions |
    Where-Object {
        $_.DisplayName -like "2.0 *"
    }
).Count -gt 0

$UseFontFix = $false

if ($Has2Point0) {

    Write-Host ""
    Write-Host "Optional fix for Minecraft 2.0:"
    Write-Host "MC-23952 - fixes wide Unicode glyph alignment."
    Write-Host ""
    Write-Host "This fix is only needed if you use a font texture pack."
    Write-Host "If you use English or the vanilla Minecraft font, you do NOT need this fix."
    Write-Host ""
    Write-Host "This will use:"
    Write-Host "  2.0 Blue/Purple  -> awz.class"
    Write-Host "  2.0 Red          -> awv.class"
    Write-Host "  2.0 Preview      -> ayb.class"
    Write-Host ""

    $FontFixChoice = Read-Host "Apply the MC-23952 font alignment fix? [Y/N]"

    if ($FontFixChoice -match "^[Yy2]$") {

        $UseFontFix = $true

        Write-Host "MC-23952 font alignment fix: ENABLED"
    }
    else {

        $UseFontFix = $false

        Write-Host "MC-23952 font alignment fix: DISABLED"
    }
}

# ============================================================
# Output format selection
# ============================================================

Write-Host ""
Write-Host "Output format:"
Write-Host "[1] .class files"
Write-Host "[2] Modded .jar files"
Write-Host ""

$OutputChoice = Read-Host "Select output format"

if ($OutputChoice -eq "1") {

    $OutputFormat = "classes"

}
elseif ($OutputChoice -eq "2") {

    $OutputFormat = "jar"

}
else {

    throw "Invalid output format."
}

Write-Host ""
Write-Host "Selected output format: $OutputFormat"

# ============================================================
# Find Java
# ============================================================

function Find-Java {

    if ($env:JAVA_HOME) {

        $javac = Join-Path $env:JAVA_HOME "bin\javac.exe"

        if (Test-Path $javac) {
            return $javac
        }
    }

    $Command = Get-Command javac.exe -ErrorAction SilentlyContinue

    if ($Command) {

        $Candidate = $Command.Source

        if (Test-Path (Join-Path (Split-Path $Candidate -Parent) "jar.exe")) {
            return $Candidate
        }
    }

    $Candidates = @(

        "D:\Program Files\Java\jdk-26.0.1\bin\javac.exe"
        "D:\Program Files\Java\jdk-25\bin\javac.exe"

        "C:\Program Files\Java\jdk-26.0.1\bin\javac.exe"
        "C:\Program Files\Java\jdk-26\bin\javac.exe"
        "C:\Program Files\Java\jdk-25\bin\javac.exe"
        "C:\Program Files\Java\jdk-24\bin\javac.exe"
        "C:\Program Files\Java\jdk-23\bin\javac.exe"
        "C:\Program Files\Java\jdk-22\bin\javac.exe"
        "C:\Program Files\Java\jdk-21\bin\javac.exe"
        "C:\Program Files\Java\jdk-17\bin\javac.exe"
        "C:\Program Files\Java\jdk-11\bin\javac.exe"

        "D:\Program Files\Java\jdk-26\bin\javac.exe"
        "D:\Program Files\Java\jdk-24\bin\javac.exe"
        "D:\Program Files\Java\jdk-23\bin\javac.exe"
        "D:\Program Files\Java\jdk-22\bin\javac.exe"
        "D:\Program Files\Java\jdk-21\bin\javac.exe"
        "D:\Program Files\Java\jdk-17\bin\javac.exe"
        "D:\Program Files\Java\jdk-11\bin\javac.exe"

    )

    foreach ($Candidate in $Candidates) {

        if (Test-Path $Candidate) {

            $CandidateDir = Split-Path $Candidate -Parent
            $JarTool = Join-Path $CandidateDir "jar.exe"

            if (Test-Path $JarTool) {
                return $Candidate
            }
        }
    }

    throw "A complete JDK with javac.exe and jar.exe was not found."
}

$Javac = Find-Java

$JavaBin = Split-Path $Javac -Parent
$JarTool = Join-Path $JavaBin "jar.exe"

Write-Host ""
Write-Host "Java compiler: $Javac"

& $Javac -version

if ($LASTEXITCODE -ne 0) {
    throw "Failed to run javac."
}

# ============================================================
# Prepare directories
# ============================================================

New-Item -ItemType Directory -Force -Path $BuildDir | Out-Null
New-Item -ItemType Directory -Force -Path $DepsDir | Out-Null

# ============================================================
# Check translation directory
# ============================================================

if (-not (Test-Path $LangDir)) {

    throw @"
Translation directory not found:

$LangDir

Create:

2.0\lang\
"@
}

if (-not (Test-Path $LanguagesFile)) {

    throw @"
languages.txt was not found:

$LanguagesFile

Create:

2.0\lang\languages.txt
"@
}

$CustomLanguageFiles = @(
    Get-ChildItem `
        -Path $LangDir `
        -Filter "*.lang" `
        -File
)

Write-Host ""
Write-Host "Translation patches found:"

if ($CustomLanguageFiles.Count -eq 0) {

    Write-Host "  None"

}
else {

    foreach ($LanguageFile in $CustomLanguageFiles) {
        Write-Host "  $($LanguageFile.Name)"
    }
}

# ============================================================
# Download helper
# ============================================================

function Download-File {

    param(
        [string]$Url,
        [string]$Destination
    )

    if (Test-Path $Destination) {

        Write-Host "Already downloaded: $(Split-Path $Destination -Leaf)"

        return
    }

    Write-Host "Downloading: $Url"

    Invoke-WebRequest `
        -Uri $Url `
        -OutFile $Destination `
        -UseBasicParsing
}

# ============================================================
# Download LWJGL
# ============================================================

Download-File $JavaJar $LwjglJar
Download-File $JavaUtilJar $LwjglUtilJar

# ============================================================
# Language helper functions
# ============================================================

function Read-Utf8File {

    param(
        [string]$Path
    )

    return [System.IO.File]::ReadAllText(
        $Path,
        [System.Text.Encoding]::UTF8
    )
}

function Convert-LangToMap {

    param(
        [string]$Content
    )

    $Map = [ordered]@{}

    $Lines = $Content -split "\r?\n"

    foreach ($Line in $Lines) {

        if ([string]::IsNullOrWhiteSpace($Line)) {
            continue
        }

        if ($Line.StartsWith("#")) {
            continue
        }

        $Equals = $Line.IndexOf("=")

        if ($Equals -le 0) {
            continue
        }

        $Key = $Line.Substring(0, $Equals)
        $Value = $Line.Substring($Equals + 1)

        $Map[$Key] = $Value
    }

    return $Map
}

# ============================================================
# Apply a translation patch
#
# Rules:
#
# 1. Existing key:
#       replace its value.
#
# 2. Missing key:
#       add it to the end.
#
# 3. All other vanilla lines:
#       remain unchanged.
# ============================================================

function Apply-LanguagePatch {

    param(
        [string]$ArchiveContent,
        [string]$PatchContent
    )

    $PatchMap = Convert-LangToMap $PatchContent

    $Lines = $ArchiveContent -split "\r?\n"

    $Result = New-Object System.Collections.Generic.List[string]

    $UsedKeys = @{}

    foreach ($Line in $Lines) {

        $Equals = $Line.IndexOf("=")

        if ($Equals -gt 0) {

            $Key = $Line.Substring(0, $Equals)

            if ($PatchMap.Contains($Key)) {

                $Result.Add(
                    "$Key=$($PatchMap[$Key])"
                )

                $UsedKeys[$Key] = $true

                continue
            }
        }

        $Result.Add($Line)
    }

    # --------------------------------------------------------
    # Add patch keys which did not exist in the vanilla file
    # --------------------------------------------------------

    foreach ($Key in $PatchMap.Keys) {

        if (-not $UsedKeys.ContainsKey($Key)) {

            $Result.Add(
                "$Key=$($PatchMap[$Key])"
            )
        }
    }

    return ($Result -join "`r`n")
}

# ============================================================
# Fix special keys for languages without a custom patch
#
# IMPORTANT:
#
# These keys are ONLY REPLACED if they already exist.
#
# They are NEVER added to a language that does not have them.
# ============================================================

function Fix-Vanilla-SpecialTranslations {

    param(
        [string]$ArchiveContent
    )

    $Lines = $ArchiveContent -split "\r?\n"

    $Result = New-Object System.Collections.Generic.List[string]

    foreach ($Line in $Lines) {

        $Equals = $Line.IndexOf("=")

        if ($Equals -gt 0) {

            $Key = $Line.Substring(0, $Equals)

            if ($Key -eq "options.anaglyph") {

                $Result.Add(
                    "options.anaglyph=Super HD Graphics"
                )

                continue
            }

            if ($Key -eq "tile.dropper.name") {

                $Result.Add(
                    "tile.dropper.name=Flopper"
                )

                continue
            }
        }

        $Result.Add($Line)
    }

    return ($Result -join "`r`n")
}

# ============================================================
# Process one language file
#
# This is shared by both:
#
# - modded JAR output
# - .class files output
#
# Therefore both output modes use exactly the same
# translation logic.
# ============================================================

function Process-LanguageContent {

    param(
        [string]$LanguageName,
        [string]$ArchiveContent
    )

    $CustomLanguagePath = Join-Path `
        $LangDir `
        $LanguageName

    if (Test-Path $CustomLanguagePath) {

        # ====================================================
        # CUSTOM PATCH EXISTS
        # ====================================================

        Write-Host "Patching language: $LanguageName"

        $PatchContent = Read-Utf8File `
            $CustomLanguagePath

        return Apply-LanguagePatch `
            $ArchiveContent `
            $PatchContent
    }
    else {

        # ====================================================
        # NO CUSTOM PATCH
        #
        # Only replace existing:
        #
        # options.anaglyph
        # tile.dropper.name
        #
        # Never add missing ones.
        # ====================================================

        Write-Host "Fixing special translations: $LanguageName"

        return Fix-Vanilla-SpecialTranslations `
            $ArchiveContent
    }
}

# ============================================================
# Read language entry from JAR
# ============================================================

function Read-LanguageEntry {

    param(
        [System.IO.Compression.ZipArchiveEntry]$Entry
    )

    $InputStream = $null
    $Reader = $null

    try {

        $InputStream = $Entry.Open()

        $Reader = New-Object System.IO.StreamReader(
            $InputStream,
            [System.Text.Encoding]::UTF8,
            $true
        )

        return $Reader.ReadToEnd()

    }
    finally {

        if ($Reader) {
            $Reader.Dispose()
        }

        if ($InputStream) {
            $InputStream.Dispose()
        }
    }
}

# ============================================================
# Write text entry to ZIP
# ============================================================

function Add-TextEntry {

    param(
        [System.IO.Compression.ZipArchive]$Archive,
        [string]$EntryName,
        [string]$Content
    )

    $Bytes = [System.Text.Encoding]::UTF8.GetBytes($Content)

    $NewEntry = $Archive.CreateEntry(
        $EntryName,
        [System.IO.Compression.CompressionLevel]::Optimal
    )

    $OutputStream = $null

    try {

        $OutputStream = $NewEntry.Open()

        $OutputStream.Write(
            $Bytes,
            0,
            $Bytes.Length
        )

    }
    finally {

        if ($OutputStream) {
            $OutputStream.Dispose()
        }
    }
}

# ============================================================
# Export processed languages to normal directory
# ============================================================

function Export-ProcessedLanguages {

    param(
        [string]$MinecraftJar,
        [string]$OutputDir
    )

    $OutputLangDir = Join-Path $OutputDir "lang"

    if (Test-Path $OutputLangDir) {
        Remove-Item $OutputLangDir -Recurse -Force
    }

    New-Item `
        -ItemType Directory `
        -Force `
        -Path $OutputLangDir | Out-Null

    Write-Host ""
    Write-Host "Preparing language files for manual installation..."

    $ArchiveLanguages = @{}
    $SourceArchive = $null

    try {

        $SourceArchive = [System.IO.Compression.ZipFile]::OpenRead(
            $MinecraftJar
        )

        # ====================================================
        # Process languages from original JAR
        # ====================================================

        foreach ($Entry in $SourceArchive.Entries) {

            $EntryName = $Entry.FullName

            if (
                $EntryName.StartsWith(
                    "lang/",
                    [System.StringComparison]::OrdinalIgnoreCase
                ) -and
                $EntryName.EndsWith(
                    ".lang",
                    [System.StringComparison]::OrdinalIgnoreCase
                )
            ) {

                $LanguageName = [System.IO.Path]::GetFileName(
                    $EntryName
                )

                $ArchiveLanguages[$LanguageName] = $true

                # --------------------------------------------
                # Read original language
                # --------------------------------------------

                $ArchiveContent = Read-LanguageEntry $Entry

                # --------------------------------------------
                # Apply exactly the same translation processing
                # as JAR output
                # --------------------------------------------

                $FinalContent = Process-LanguageContent `
                    $LanguageName `
                    $ArchiveContent

                # --------------------------------------------
                # Write processed language
                # --------------------------------------------

                $OutputLanguagePath = Join-Path `
                    $OutputLangDir `
                    $LanguageName

                [System.IO.File]::WriteAllText(
                    $OutputLanguagePath,
                    $FinalContent,
                    [System.Text.Encoding]::UTF8
                )
            }
        }

        # ====================================================
        # Add custom languages which do not exist in JAR
        # ====================================================

        foreach ($LanguageFile in $CustomLanguageFiles) {

            $LanguageName = $LanguageFile.Name

            if ($ArchiveLanguages.ContainsKey($LanguageName)) {
                continue
            }

            Write-Host "Adding new language: $LanguageName"

            $Content = Read-Utf8File `
                $LanguageFile.FullName

            $OutputLanguagePath = Join-Path `
                $OutputLangDir `
                $LanguageName

            [System.IO.File]::WriteAllText(
                $OutputLanguagePath,
                $Content,
                [System.Text.Encoding]::UTF8
            )
        }

        # ====================================================
        # Replace languages.txt
        # ====================================================

        Write-Host "Adding custom: lang/languages.txt"

        Copy-Item `
            $LanguagesFile `
            (Join-Path $OutputLangDir "languages.txt") `
            -Force

    }
    finally {

        if ($SourceArchive) {
            $SourceArchive.Dispose()
        }
    }

    Write-Host "Language files prepared: $OutputLangDir"
}

# ============================================================
# Build one version
# ============================================================

function Build-Version {

    param(
        [hashtable]$Info
    )

    Write-Host ""
    Write-Host "============================================================"
    Write-Host "Building $($Info.DisplayName)"
    Write-Host "============================================================"

    $MinecraftJar = Join-Path $DepsDir $Info.JarName

    Download-File $Info.Url $MinecraftJar

    # ========================================================
    # Verify Minecraft .jar
    # ========================================================

    $Hash = (Get-FileHash $MinecraftJar -Algorithm SHA256).Hash

    if ($Hash.ToUpper() -ne $Info.Sha256.ToUpper()) {

        Remove-Item $MinecraftJar -Force

        throw @"
SHA-256 mismatch for $($Info.DisplayName)!

Expected:
$($Info.Sha256)

Got:
$Hash

The downloaded file was deleted.
"@
    }

    Write-Host "SHA-256 OK."

    # ========================================================
    # Prepare output directory
    # ========================================================

    $OutputDir = Join-Path $BuildDir $Info.Id

    if (Test-Path $OutputDir) {
        Remove-Item $OutputDir -Recurse -Force
    }

    New-Item -ItemType Directory -Force -Path $OutputDir | Out-Null

    # ========================================================
    # Prepare source/class lists
    # ========================================================

    $SourcesToCompile = @(
        $Info.Sources
    )

    $ClassesToReplace = @(
        $Info.Classes
    )

    if ($UseFontFix -and $Info.FontFixSource) {

        $SourcesToCompile += $Info.FontFixSource
        $ClassesToReplace += $Info.FontFixClass

        Write-Host ""
        Write-Host "Font centering fix enabled:"
        Write-Host "  Source: $($Info.FontFixSource)"
        Write-Host "  Class:  $($Info.FontFixClass)"
    }

    $SourcesToCompile = @(
        $SourcesToCompile | Select-Object -Unique
    )

    $ClassesToReplace = @(
        $ClassesToReplace | Select-Object -Unique
    )

    # ========================================================
    # Compile sources
    # ========================================================

    foreach ($SourceRelative in $SourcesToCompile) {

        $Source = Join-Path $Root $SourceRelative

        if (-not (Test-Path $Source)) {
            throw "Source file not found: $Source"
        }

        $SourceName = [System.IO.Path]::GetFileNameWithoutExtension($Source)

        $ClassOutput = Join-Path $OutputDir "$SourceName.class"

        Write-Host ""
        Write-Host "Compiling $SourceRelative"

        $Classpath = "$MinecraftJar;$LwjglJar;$LwjglUtilJar"

        & $Javac `
            --release 8 `
            -cp $Classpath `
            -d $OutputDir `
            $Source

        if ($LASTEXITCODE -ne 0) {
            throw "Compilation failed: $SourceRelative"
        }

        if (-not (Test-Path $ClassOutput)) {
            throw "Compilation finished but $ClassOutput was not created."
        }

        Write-Host "Created: $ClassOutput"
    }

    # ========================================================
    # Export processed languages for .class files
    # ========================================================

    if ($OutputFormat -eq "classes") {

        Export-ProcessedLanguages `
            $MinecraftJar `
            $OutputDir
    }

    # ========================================================
    # Create modded .jar
    # ========================================================

    if ($OutputFormat -eq "jar") {

        $ModdedJar = Join-Path $OutputDir "$($Info.Id)-moded.jar"
        $TempJar = Join-Path $OutputDir "$($Info.Id)-moded-temp.jar"

        Write-Host ""
        Write-Host "Creating modded .jar without extracting..."

        # ----------------------------------------------------
        # Check compiled classes
        # ----------------------------------------------------

        foreach ($ClassName in $ClassesToReplace) {

            $CompiledClass = Join-Path $OutputDir $ClassName

            if (-not (Test-Path $CompiledClass)) {
                throw "Compiled class not found: $ClassName"
            }
        }

        # ----------------------------------------------------
        # Remove old temporary archives
        # ----------------------------------------------------

        if (Test-Path $TempJar) {
            Remove-Item $TempJar -Force
        }

        if (Test-Path $ModdedJar) {
            Remove-Item $ModdedJar -Force
        }

        # ----------------------------------------------------
        # Track language files from original JAR
        # ----------------------------------------------------

        $ArchiveLanguages = @{}

        $SourceArchive = $null
        $OutputArchive = $null

        try {

            $SourceArchive = [System.IO.Compression.ZipFile]::OpenRead(
                $MinecraftJar
            )

            $OutputArchive = [System.IO.Compression.ZipFile]::Open(
                $TempJar,
                [System.IO.Compression.ZipArchiveMode]::Create
            )

            # =================================================
            # Copy original JAR
            # =================================================

            foreach ($Entry in $SourceArchive.Entries) {

                $EntryName = $Entry.FullName

                # ---------------------------------------------
                # Completely remove META-INF
                # ---------------------------------------------

                if (
                    $EntryName -eq "META-INF" -or
                    $EntryName -eq "META-INF/" -or
                    $EntryName.StartsWith(
                        "META-INF/",
                        [System.StringComparison]::OrdinalIgnoreCase
                    )
                ) {

                    Write-Host "Removed: $EntryName"

                    continue
                }

                # ---------------------------------------------
                # Skip classes that will be replaced
                # ---------------------------------------------

                $ReplaceClass = $false

                foreach ($ClassName in $ClassesToReplace) {

                    if ($EntryName -eq $ClassName) {

                        $ReplaceClass = $true

                        break
                    }
                }

                if ($ReplaceClass) {

                    Write-Host "Replacing: $EntryName"

                    continue
                }

                # =================================================
                # LANGUAGE FILES
                # =================================================

                if (
                    $EntryName.StartsWith(
                        "lang/",
                        [System.StringComparison]::OrdinalIgnoreCase
                    )
                ) {

                    # ---------------------------------------------
                    # languages.txt is always replaced
                    # ---------------------------------------------

                    if (
                        $EntryName.Equals(
                            "lang/languages.txt",
                            [System.StringComparison]::OrdinalIgnoreCase
                        )
                    ) {

                        Write-Host "Replacing: $EntryName"

                        continue
                    }

                    # ---------------------------------------------
                    # Process .lang files
                    # ---------------------------------------------

                    if (
                        $EntryName.EndsWith(
                            ".lang",
                            [System.StringComparison]::OrdinalIgnoreCase
                        )
                    ) {

                        $LanguageName = [System.IO.Path]::GetFileName(
                            $EntryName
                        )

                        $ArchiveLanguages[$LanguageName] = $true

                        # -----------------------------------------
                        # Read original language
                        # -----------------------------------------

                        $ArchiveContent = Read-LanguageEntry $Entry

                        # -----------------------------------------
                        # Apply shared translation processing
                        # -----------------------------------------

                        $FinalContent = Process-LanguageContent `
                            $LanguageName `
                            $ArchiveContent

                        # -----------------------------------------
                        # Write language into output JAR
                        # -----------------------------------------

                        Add-TextEntry `
                            $OutputArchive `
                            $EntryName `
                            $FinalContent

                        continue
                    }
                }

                # =================================================
                # NORMAL FILE
                # =================================================

                $NewEntry = $OutputArchive.CreateEntry(
                    $EntryName,
                    [System.IO.Compression.CompressionLevel]::Optimal
                )

                if ($Entry.Length -gt 0) {

                    $InputStream = $null
                    $OutputStream = $null

                    try {

                        $InputStream = $Entry.Open()
                        $OutputStream = $NewEntry.Open()

                        $InputStream.CopyTo($OutputStream)

                    }
                    finally {

                        if ($OutputStream) {
                            $OutputStream.Dispose()
                        }

                        if ($InputStream) {
                            $InputStream.Dispose()
                        }
                    }
                }
            }

            # =================================================
            # ADD LANGUAGES WHICH DO NOT EXIST IN ORIGINAL JAR
            # =================================================

            foreach ($LanguageFile in $CustomLanguageFiles) {

                $LanguageName = $LanguageFile.Name

                if ($ArchiveLanguages.ContainsKey($LanguageName)) {
                    continue
                }

                Write-Host "Adding new language: $LanguageName"

                $Content = Read-Utf8File `
                    $LanguageFile.FullName

                Add-TextEntry `
                    $OutputArchive `
                    "lang/$LanguageName" `
                    $Content
            }

            # =================================================
            # REPLACE languages.txt
            # =================================================

            Write-Host "Adding custom: lang/languages.txt"

            $LanguagesContent = Read-Utf8File `
                $LanguagesFile

            Add-TextEntry `
                $OutputArchive `
                "lang/languages.txt" `
                $LanguagesContent

            # =================================================
            # ADD MODIFIED CLASSES
            # =================================================

            foreach ($ClassName in $ClassesToReplace) {

                $CompiledClass = Join-Path `
                    $OutputDir `
                    $ClassName

                Write-Host "Adding modified class: $ClassName"

                $NewEntry = $OutputArchive.CreateEntry(
                    $ClassName,
                    [System.IO.Compression.CompressionLevel]::Optimal
                )

                $InputStream = $null
                $OutputStream = $null

                try {

                    $InputStream = [System.IO.File]::OpenRead(
                        $CompiledClass
                    )

                    $OutputStream = $NewEntry.Open()

                    $InputStream.CopyTo($OutputStream)

                }
                finally {

                    if ($OutputStream) {
                        $OutputStream.Dispose()
                    }

                    if ($InputStream) {
                        $InputStream.Dispose()
                    }
                }
            }

        }
        finally {

            if ($OutputArchive) {
                $OutputArchive.Dispose()
            }

            if ($SourceArchive) {
                $SourceArchive.Dispose()
            }
        }

        # ========================================================
        # Move temporary JAR to final location
        # ========================================================

        Move-Item `
            $TempJar `
            $ModdedJar `
            -Force

        # ========================================================
        # Remove compiled class files
        # ========================================================

        foreach ($ClassName in $ClassesToReplace) {

            $CompiledClass = Join-Path `
                $OutputDir `
                $ClassName

            if (Test-Path $CompiledClass) {
                Remove-Item $CompiledClass -Force
            }
        }

        Write-Host "Created: $ModdedJar"
    }
}

# ============================================================
# Build selected versions
# ============================================================

try {

    foreach ($Version in $SelectedVersions) {
        Build-Version $Version
    }

    # ========================================================
    # Build successful
    # ========================================================

    Write-Host ""
    Write-Host "============================================================" -ForegroundColor Green
    Write-Host "BUILD SUCCESSFUL" -ForegroundColor Green
    Write-Host "============================================================" -ForegroundColor Green
    Write-Host ""

    Write-Host "Output:"

    foreach ($Version in $SelectedVersions) {

        if ($OutputFormat -eq "jar") {

            Write-Host "  build\$($Version.Id)\$($Version.Id)-moded.jar"

        }
        else {

            Write-Host "  build\$($Version.Id)\"
        }
    }

    # ========================================================
    # Clean build cache
    # ========================================================

    Write-Host ""

    $CleanCache = Read-Host "Clean build cache (.build-deps)? [Y/N]"

    if ($CleanCache -match "^[Yy2]$") {

        if (Test-Path $DepsDir) {

            Remove-Item $DepsDir -Recurse -Force

            Write-Host "Build cache removed."

        }
        else {

            Write-Host "Build cache is already empty."
        }
    }
    else {

        Write-Host "Build cache kept."
    }

}
catch {

    Write-Host ""
    Write-Host "============================================================" -ForegroundColor Red
    Write-Host "BUILD FAILED" -ForegroundColor Red
    Write-Host "============================================================" -ForegroundColor Red
    Write-Host ""
    Write-Host $_.Exception.Message -ForegroundColor Red
}
finally {

    Write-Host ""
    Write-Host "============================================================"
    Write-Host "SCRIPT FINISHED"
    Write-Host "============================================================"
    Write-Host ""

    Read-Host "Press Enter to close"
}