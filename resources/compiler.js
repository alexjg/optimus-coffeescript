function compile(source, filename){
    try {
        result = CoffeeScript.compile(source, {filename: filename});
        return {
            success: true,
            code: result,
        }
    } catch (e) {
        return {
            success: false,
            errorLine: e.location.first_line + 1,
            errorCharacter: e.location.first_column,
            errorMessage: e.message,
            errorDescription: e.toString()
        }
    }
}
