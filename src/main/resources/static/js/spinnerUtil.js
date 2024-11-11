
let spinnerModal;
let spinner;
let messageTimeout;
let loadingMessage;

function showSpinner() {
    return new Promise((resolve) => {
        if (!spinnerModal) {
            const modalHtml = `
                <div class="modal fade" id="spinnerModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content" style="background: transparent; border: none;">
                            <div class="modal-body text-center">
                                <div id="spinnerContainer">
                                </div>
                                <div id="loadingMessage" class="animated-message" style="margin-top: 90px;">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            document.body.insertAdjacentHTML('beforeend', modalHtml);
            spinnerModal = new bootstrap.Modal(document.getElementById("spinnerModal"), {
                backdrop: 'static',
                keyboard: false
            });
        }

        const spinnerContainer = document.getElementById('spinnerContainer');
        spinner = new Spinner().spin(spinnerContainer);

        const spinnerModalElement = document.getElementById('spinnerModal');
        loadingMessage = document.getElementById("loadingMessage");

        loadingMessage.textContent = "It is being executed now ...";

        messageTimeout = setTimeout(() => {
            loadingMessage.textContent = "Please wait litte more ...";
        }, 5000);

        // we wait for the modal to be fully shown
        spinnerModalElement.addEventListener('shown.bs.modal', function handler() {
            spinnerModalElement.removeEventListener('shown.bs.modal', handler);
            resolve();
        });

        spinnerModal.show();
    });
}

function hideSpinner() {
    return new Promise((resolve) => {
        if (spinnerModal) {
            const spinnerModalElement = document.getElementById('spinnerModal');

            spinnerModalElement.addEventListener('hidden.bs.modal', function handler() {
                spinnerModalElement.removeEventListener('hidden.bs.modal', handler);

                // Remove the modal from the DOM
                spinnerModalElement.parentNode.removeChild(spinnerModalElement);

                // Reset variables
                spinnerModal = null;
                spinner = null;

                resolve();
            });

            clearTimeout(messageTimeout);
            loadingMessage.textContent = "";

            if (spinner) {
                spinner.stop();
            }

            spinnerModal.hide();
        } else {
            resolve();
        }
    });
}
