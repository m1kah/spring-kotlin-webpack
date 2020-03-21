import("@vaadin/vaadin-combo-box");
import("@vaadin/vaadin-date-picker");
import("@vaadin/vaadin-select");
import("@vaadin/vaadin-button");

const isoDate = date => date.toISOString().substring(0, 10);

customElements.whenDefined("vaadin-date-picker").then(_ => {
    const now = new Date();
    const startOfYear = new Date(2020, 0, 1);
    const datePicker = document.getElementById("date-picker");
    datePicker.i18n.formatDate = d => { return `${d.day}.${d.month + 1}.${d.year}` };
    datePicker.value = isoDate(now);
    datePicker.max = isoDate(now);
    datePicker.min = isoDate(startOfYear);
});

const instructionSetArchitectures = [
    {
        type: "RISC",
        name: "Reduced instruction set computer"
    },
    {
        type: "CICS",
        name: "Complex instruction set computer"
    },
    {
        type: "MISC",
        name: "Minimal instruction set computer"
    },
    {
        type: "LIW",
        name: "Long instruction word"
    },
    {
        type: "VLIW",
        name: "Very long instruction word"
    },
    {
        type: "EPIC",
        name: "Explicitly parallel instruction computing"
    }
];

customElements.whenDefined("vaadin-combo-box").then(_ => {
    const comboBox = document.getElementById("combo-box");
    comboBox.itemIdPath = "type";
    comboBox.itemLabelPath = "name";
    comboBox.items = instructionSetArchitectures;
    // Not working?
    comboBox.value = instructionSetArchitectures[1].type;
});

customElements.whenDefined("vaadin-button").then(_ => {
    const button = document.getElementById("button");
    button.onclick = event => {
        const p = new Promise((accept, reject) => {
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "/api/submit");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader("Accept", "application/json");
            xhr.onreadystatechange = () => {
                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                    const text = xhr.responseText;
                    const json = JSON.parse(text);
                    accept(json);
                }
            };
            const json = document.getElementById("combo-box").selectedItem;
            const raw = JSON.stringify(json);
            xhr.send(raw);
        });
        p.then(responseJson => {
            document.getElementById("reply").textContent = responseJson.message || "no message :("
        });
        console.log("check");
    }
});
