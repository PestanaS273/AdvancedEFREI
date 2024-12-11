import pytest
import pandas as pd
from excel_analysis_etudiant import run_analysis

@pytest.fixture
def mock_excel_file(tmp_path):
    file_path = tmp_path / "mock_file.xlsx"
    df = pd.DataFrame({
        "codeEtudiant": [1, 2],
        "prenom": ["John", "Jane"],
        "nom": ["Doe", "Smith"],
        "dateNaissance": ["2000-01-01", "2001-01-01"],
        "email": ["john@example.com", "jane@example.com"],
        "telephone": ["1234567890", "0987654321"],
        "coursNom": ["Math,Science", "History"]
    })
    df.to_excel(file_path, index=False)
    return file_path

def test_run_analysis(mock_excel_file):
    run_analysis(mock_excel_file)
